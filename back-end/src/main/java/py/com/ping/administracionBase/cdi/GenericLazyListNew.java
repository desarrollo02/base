package py.com.ping.administracionBase.cdi;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Remove;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Level;
import javax.persistence.PersistenceContext;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 *
 *
 * @modificado: Guillermo Dominguez
 */
public class GenericLazyListNew<T> extends LazyDataModel<T> implements Serializable {

    private static final Logger log
            = LoggerFactory.getLogger(GenericLazyListNew.class);

    protected Class<T> clase;
    @PersistenceContext
    private EntityManager em;
    private List<T> lazyList;
    private BigDecimal sumaTotal;
    private Object[] objetosSumas;
    private Map<String, Object> camposMap;
    private Map<String, Object> camposFiltrosAll;
    private String queryActual;
    private String whereActual;
    List<T> selectedListObj = new LinkedList<>();

    public GenericLazyListNew(EntityManager em, Class<T> clase) {
        this.em = em;
        this.clase = clase;

    }

    /**
     * Con este metodo solo ya se necesita recibir las columnas y los valores
     * por los cuales se quiere filtrar el objeto cada vez que es renderizado,
     * no es necesario enviar las columnas a filtrar en el DataModel eso
     * automaticamente se encarga el LazyDataModel en capturar.
     *
     * @param c
     * @param campos
     * @return LazyDataModel
     */
    public LazyDataModel getLazyDataModel(Class c, Map<String, Object> campos) {

        try {
            setCamposMap(campos);
            setClase(c);

            this.setRowCount(this.getSearchResultCount(getClase(), campos));
        } catch (Exception e) {
            log.error("error en getLazyDataModel", e);
        }
        return this;
    }

    public LazyDataModel getLazyDataModel(Class c, Map<String, Object> campos, Map<String, Object> camposFiltro) {
        try {
            setCamposMap(campos);
            setClase(c);
            setCamposFiltrosAll(camposFiltro);
            this.setRowCount(this.getSearchResultCount(getClase(), campos));
        } catch (Exception e) {
            log.error("error en getLazyDataModel", e);
        }
        return this;
    }

    @Override
    public String getRowKey(T object) {
        try {
            try {
                return BeanUtils.getProperty(object, "id");
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(GenericLazyListNew.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                java.util.logging.Logger.getLogger(GenericLazyListNew.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchMethodException ex) {
                java.util.logging.Logger.getLogger(GenericLazyListNew.class.getName()).log(Level.SEVERE, null, ex);
            }
            //       return (String)object.getClass("id"); //To change body of generated methods, choose Tools | Templates.  
        } catch (SecurityException ex) {
            java.util.logging.Logger.getLogger(GenericLazyListNew.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    public Object getRowKey(Class car) throws NoSuchFieldException {
//        return car.getDeclaredField("id");
//    }
    @Override
    public List<T> load(int first, int pageSize, Map<String, SortMeta> sort, Map<String, FilterMeta> filters) {
        return this.getSearchResult(first, pageSize, filters, getCamposMap(), sort);
    }

    @Override
    public T getRowData(String rowKey) {
        System.out.println("rowKey: " + rowKey);

        try {
            if (Integer.class.isAssignableFrom(clase.getDeclaredField("id").getType())) {
                return em.find(clase, new Integer(rowKey));
            } else if (Long.class.isAssignableFrom(clase.getDeclaredField("id").getType())) {
                return em.find(clase, new Long(rowKey));
            } else {
                return em.find(clase, rowKey);
            }
        } catch (NoSuchFieldException | SecurityException ex) {
            log.error("Error en generic getRowData", ex);
        }
        return em.find(clase, rowKey);
    }

    public List<T> getSelectedListObj() {
        return new LinkedList<>(selectedListObj);
    }

    public void setSelectedListObj(List<T> selectedListObj) {
        this.selectedListObj = new LinkedList<>(selectedListObj); // defensive copy

    }

    public boolean filterByPrice(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null || filterText.equals("")) {
            return true;
        }

        if (value == null) {
            return false;
        }

        return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
    }

    /**
     *
     * Metodo para consultar la cantidad de filas a mostrar de acuerdo a los
     * filtros especificados en el controlador correspondiente.
     */
    private Integer getSearchResultCount(Class c, Map<String, Object> campos) {
        StringBuilder queryCount = new StringBuilder("SELECT COUNT(b) FROM "
                + getClase().getSimpleName() + (filtrosVistas != null ? filtrosVistas : "") + " b");
        StringBuilder where = null;

        boolean whereAdded = false;
        String s3, sCount;
        boolean addAdded = false;
        boolean orderByAdded = false;
        String campoOrdenacion = "";
        boolean groupByAdded = false;
        String campoAgrupacion = "";

        String orden = "";
        String campoSuma = "";
        boolean sumaAdded = false;
        where = new StringBuilder(" WHERE ");
        Long countRow;
        try {
            for (Map.Entry<String, Object> pairs : campos.entrySet()) {
                // System.out.print("Campo Where lazy "+wh);
//                log.debug("Key Campos.......................... = "                      + pairs.getKey());

                if (pairs.getKey().equalsIgnoreCase("JOIN")) {
                    where = new StringBuilder(" ");
                    where.append(pairs.getValue());
                    //where.append(" WHERE ");
                    //whereAdded = false;
                    whereAdded = true;
                    continue;
                }

                addAdded = false;
                if (whereAdded == true) {
                    where.append(" AND ");
                    addAdded = true;
                }
                if (pairs.getKey().equalsIgnoreCase("where")) {

                    where.append(pairs.getValue());

                } else if (pairs.getValue() instanceof Number) {
                    where.append("b.").append(pairs.getKey()).append(" = ");
                    where.append(pairs.getValue()).append("");
                } else if (pairs.getValue() instanceof Date) {
                    where.append("b.").append(pairs.getKey()).append(" >= ");
                    where.append(pairs.getValue()).append("");
                    where.append(" AND b.").append(pairs.getKey())
                            .append(" <= ");
                    where.append(pairs.getValue()).append("");
                } else if ((pairs.getValue() instanceof List)) {
                    where.append("b.").append(pairs.getKey()).append(" IN ( ");
                    where.append(pairs.getValue()).append(")");
                }/**
                 * La sgte opcion fue agregada para poder agregar un operador
                 * relacional definido por el usuario, el formato para acceder a
                 * esta opcion es especificar en el HashMap un campo como el
                 * siguiente formato: campos.put("-1.operador:estado: not like
                 * ", "'B'"); el valor "-1.operador:" se pone para acceder a
                 * esta opcion; acontinuacion del ":" se especifica el campo(en
                 * este ejemplo: estado) y luego de ":" operador relacional
                 * deseado (en este ejemplo: not like)
                 *
                 */
                else if ((pairs.getKey().indexOf("-1.operador:")) != -1) {
                    String[] operador = pairs.getKey().split(":");
                    if (operador[2].indexOf("OR") != -1) {
                        where.append("(b.").append(operador[1]).append(" OR ")
                                .append("b.").append(pairs.getValue())
                                .append(")");
                    } else if (operador[2].indexOf("BY") != -1) {
                        orderByAdded = true;
                        campoOrdenacion = operador[1];
                        orden = pairs.getValue().toString();
                        if (addAdded) {
                            where.append("1=1");
                        }
                        continue;

                    } else if (operador[2].indexOf("LIKE") != -1) {
                        where.append("upper(b.").append(operador[1]).append(")").append(operador[2]);
                        where.append(pairs.getValue());

                    } else if (operador[2].indexOf("GROUP") != -1) {
                        groupByAdded = true;
                        campoAgrupacion = operador[1];
                        // orden = pairs.getValue().toString();
                        if (addAdded) {
                            where.append("1=1");
                        }
                        continue;

                    } else {
                        where.append("b.").append(operador[1])
                                .append(operador[2]);
                        where.append(pairs.getValue());
                    }
                } else if ((pairs.getKey().indexOf("-1.operadorSuma:")) != -1) {
                    if (!whereAdded) {
                        where.append("1=1 ");
                    }
                    String[] operador = pairs.getKey().split(":");

                    if (cantCampoSuma <= 1) {
                        campoSuma = " SUM (b." + (operador[1]) + ")";
                    } else {
                        campoSuma = (operador[1]);
                    }

                    sumaAdded = true;

                } else {
                    where.append("b.").append(pairs.getKey()).append(" = '");
                    where.append(pairs.getValue().toString().toUpperCase())
                            .append("'");
                }
                whereAdded = true;
            }
            if (groupByAdded) {
                where.append(" group by ").append(campoAgrupacion);
            }
            //if(orderByAdded)
            //  where.append(" ORDER BY b.").append(campoOrdenacion).append(orden);
            String sCountAux = queryCount.toString();
            String s2 = where.toString();
            if (whereAdded == true) {
                sCount = sCountAux + s2;
            } else {
                sCount = sCountAux;
            }
            javax.persistence.Query qCount = em.createQuery(sCount, Long.class);
            countRow = (Long) qCount.getSingleResult();
        } catch (Exception e) {

//            log.error("Error en getSearchResultCount", e);
            Object count = null;

            return 0;
        }
        return Integer.valueOf(((Long) countRow).intValue());
    }

    private Field obtieneField(String clase, String f, Class claseRelacion) {
        Field field = null;
        Class c = null;
        try {
            c = claseRelacion.getDeclaredField(clase).getType();
            field = c.getDeclaredField(f);
        } catch (NoSuchFieldException ex) {
//            log.error("error", ex);
            int inicio = f.indexOf(".");
            if (inicio > 0) {
                field = obtieneField(f.substring(0, inicio), f.substring(inicio + 1, f.length()), c);
            }
        }
        return field;
    }
    private String filtrosVistas;
    private Integer cantCampoSuma = 1;

    public List getSearchResult(int firstResult, int pageSize,
            Map<String, FilterMeta> filters, Map<String, Object> campos, Map<String, SortMeta> sort) {
        log.trace("filters: " + filters);
        log.trace("sort: " + sort);

        StringBuilder query = new StringBuilder("SELECT b FROM "
                + getClase().getSimpleName() + (filtrosVistas != null ? filtrosVistas : "") + " b");
        StringBuilder queryCount = new StringBuilder("SELECT COUNT(b) FROM "
                + getClase().getSimpleName() + (filtrosVistas != null ? filtrosVistas : "") + " b");
        StringBuilder where = null;
        StringBuilder whereOrden = null;

        String filterAllValue = filters.get("globalFilter") != null ? filters.get("globalFilter").getFilterValue().toString() : null;
        boolean fillerAllAdd = filterAllValue != null ? !filterAllValue.isEmpty() : false;
        boolean whereAdded = false;
        boolean whereAddedFilter = false;

        boolean addAdded = false;
        String s3, sCount;
        s3 = "";
        boolean orderByAdded = false;
        String campoOrdenacion = "";
        boolean groupByAdded = false;
        String campoAgrupacion = "";
        String orden = "";
        String valorAComparar = "";
        String campoSuma = "";
        String finalizacion = "";
        String operadorComparacion = "";
        boolean sumaAdded = false;

        where = new StringBuilder(" WHERE ");
        Field field = null;

        try {

            for (Map.Entry<String, Object> pairs : campos.entrySet()) {
                // System.out.print("Campo Where lazy "+wh);
//                log.debug("Key Campos.......................... = "                        + pairs.getKey());

                if (pairs.getKey().equalsIgnoreCase("JOIN")) {
                    where = new StringBuilder(" ");
                    where.append(pairs.getValue());
                    //where.append(" WHERE ");
                    //whereAdded = false;
                    whereAdded = true;
                    continue;
                }
                addAdded = false;
                if (whereAdded == true) {
                    where.append(" AND ");
                    addAdded = true;
                }
                if (pairs.getKey().equalsIgnoreCase("where")) {

                    where.append(pairs.getValue());

                } else if (pairs.getValue() instanceof Number) {
                    where.append("b.").append(pairs.getKey()).append(" = ");
                    where.append(pairs.getValue()).append("");
                } else if (pairs.getValue() instanceof Date) {
                    where.append("b.").append(pairs.getKey());
                    where.append(pairs.getValue());

                    where.append("");
                } else if ((pairs.getValue() instanceof List)) {
                    where.append("b.").append(pairs.getKey()).append(" IN ( ");
                    where.append(pairs.getValue()).append(")");
                }/**
                 * La sgte opcion fue agregada para poder agregar un operador
                 * relacional definido por el usuario, el formato para acceder a
                 * esta opcion es especificar en el HashMap un campo como el
                 * siguiente formato: campos.put("-1.operador:estado: not like
                 * ", "'B'"); el valor "-1.operador:" se pone para acceder a
                 * esta opcion; acontinuacion del ":" se especifica el campo(en
                 * este ejemplo: estado) y luego de ":" operador relacional
                 * deseado (en este ejemplo: not like)
                 *
                 */
                else if ((pairs.getKey().indexOf("-1.operador:")) != -1) {
                    String[] operador = pairs.getKey().split(":");
                    if (operador[2].indexOf("OR") != -1) {
                        where.append("(b.").append(operador[1]).append(" OR ")
                                .append("b.").append(pairs.getValue())
                                .append(")");
                    } else if (operador[2].indexOf("BY") != -1) {
                        orderByAdded = true;
                        campoOrdenacion = operador[1];
                        orden = pairs.getValue().toString();
                        if (addAdded) {
                            where.append("1=1");
                        }
                        continue;

                    } else if (operador[2].indexOf("GROUP") != -1) {
                        groupByAdded = true;
                        campoAgrupacion = operador[1];
                        // orden = pairs.getValue().toString();
                        if (addAdded) {
                            where.append("1=1");
                        }
                        continue;

                    } else if (operador[2].indexOf("LIKE") != -1) {

                        where.append("upper(b.").append(operador[1]).append(")").append(operador[2]);
                        where.append(pairs.getValue());

                    } else {
                        where.append("b.").append(operador[1])
                                .append(operador[2]);
                        where.append(pairs.getValue());
                    }
                } else if ((pairs.getKey().indexOf("-1.operadorSuma:")) != -1) {
                    if (!whereAdded) {
                        where.append("  1=1 ");
                    } else {
                        where.append(" 1=1 ");
                    }
                    String[] operador = pairs.getKey().split(":");
                    if (cantCampoSuma <= 1) {
                        campoSuma = " SUM (b." + (operador[1]) + ")";
                    } else {
                        campoSuma = (operador[1]);
                    }
                    sumaAdded = true;

                } else {
                    where.append("b.").append(pairs.getKey()).append(" = '");
                    where.append(pairs.getValue() != null ? pairs.getValue().toString().toUpperCase() : null)
                            .append("'");
                }
                whereAdded = true;
            }

            for (Map.Entry<String, FilterMeta> pairs : filters.entrySet()) {

                if (!pairs.getKey().equals("globalFilter") && pairs.getValue().getFilterValue() != null) {
                    if (whereAdded == true) {
                        where.append(" AND ");
                    }
                    try {
                        log.debug("Clase Filter.......................... = " + getClase());
                        log.debug("Key Filter.......................... = " + pairs.getKey());
                        // gdb: obtiene recursivamente los filtros para las clases
                        // relacionadas
                        try {
                            field = getClase().getDeclaredField(pairs.getKey());
                        } catch (NoSuchFieldException ex) {
//                        log.error("error", ex);
                            int inicio = pairs.getKey().indexOf(".");
                            // StringTokenizer tokens=new
                            // StringTokenizer(pairs.getKey(),".");
                            if (inicio > 0) {
                                field = obtieneField(
                                        pairs.getKey().substring(0, inicio),
                                        pairs.getKey().substring(inicio + 1,
                                                pairs.getKey().length()),
                                        getClase());
                            }
                        } catch (Exception e) {
                            log.error("error", e);
                        }
                        Class type = field.getType();
                        FilterMeta meta = pairs.getValue();

                        log.debug("Clase Type.......................... = " + type);

                        if (Number.class.isAssignableFrom(type) || Integer.class.isAssignableFrom(type)) {
                            log.debug("Clase Type.......................... = " + meta.getFilterValue());

                            String signo = "=";
                            //String value=pairs.getKey().substring(0, pairs.getKey().length());
                            if ((meta.getFilterValue().toString().indexOf(">")) != -1) {
                                signo = " ";
                            } else if ((meta.getFilterValue().toString().indexOf("<")) != -1) {
                                signo = " ";
                            }
                            where.append("b.").append(pairs.getKey()).append(signo);
                            where.append(meta.getFilterValue());
                            whereAdded = true;
                        } else if (String.class.isAssignableFrom(type)) {
                            String signo = "";

                            /*Para buscar por la palabra que comienza es por defecto
                         Para buscar por la palabra que contiene el filtro debe
                         empezar con el signo %*/
                            signo = "%";
                            finalizacion = "%";
                            operadorComparacion = "LIKE";
                            valorAComparar = pairs.getValue().getFilterValue().toString();
                            if ((pairs.getValue().getFilterValue().toString().indexOf("=")) != -1) {
                                finalizacion = "";
                                signo = "";
                                operadorComparacion = "=";
                                valorAComparar = pairs.getValue().getFilterValue().toString().substring(1, valorAComparar.length());

                                System.out.println("py.com.ping.py.com.ping.administracionBase.cdi.GenericLazyListNew.getSearchResult()----->ENTREEEE");
                            }
                            System.out.println("py.com.ping.py.com.ping.administracionBase.cdi.GenericLazyListNew.getSearchResult()----->NO ENTREEEE");

//                            if ((pairs.getKey().indexOf("*")) != -1) {
//                                finalizacion = "%";
//                                signo="%";
//                            }
                            where.append("UPPER(").append("b.")
                                    .append(pairs.getKey()).append(")")
                                    .append(operadorComparacion + " '" + signo);
                            where.append(valorAComparar.toUpperCase()).append(
                                    finalizacion + "' ");
                            whereAdded = true;
                        } else if (Date.class.isAssignableFrom(type)) {
                            System.err.println("**************ES FECHAAAA");
                            String concat = "";
                            String[] operadorFecha = pairs.getValue().getFilterValue().toString().split("-");
                            if (operadorFecha[0] != null) {
                                where.append(" b.").append(pairs.getKey()).append(" >=");
                                where.append(" func('TO_DATE','" + operadorFecha[0] + "','dd/MM/yyyy')");
                                concat = " and ";
                            }
                            if (operadorFecha != null && operadorFecha.length > 1) {
                                where.append(concat).append("b.").append(pairs.getKey()).append(" <=");
                                where.append(" func('TO_DATE','" + operadorFecha[1] + "','dd/MM/yyyy')");
                            }

                        } else if (Boolean.class.isAssignableFrom(type)) {
                            System.err.println("**************ES BOOLEAN");
                            String concat = "";
                            String[] operadorFecha = pairs.getValue().getFilterValue().toString().split("-");
                            if (operadorFecha[0] != null) {
                                where.append(" b.").append(pairs.getKey()).append(" >=");
                                where.append(" func('TO_DATE','" + operadorFecha[0] + "','dd/MM/yyyy')");
                                concat = " and ";
                            }
                            if (operadorFecha != null && operadorFecha.length > 1) {
                                where.append(concat).append("b.").append(pairs.getKey()).append(" <=");
                                where.append(" func('TO_DATE','" + operadorFecha[1] + "','dd/MM/yyyy')");
                            }

                        } else if (Enum.class.isAssignableFrom(type)) {
                            String signo = "";
                            System.err.println("**************ENTRE ENUM");
                            signo = "%";
                            finalizacion = "%";
                            operadorComparacion = "LIKE";
                            valorAComparar = pairs.getValue().getFilterValue().toString();
                            where.append("UPPER(").append("CAST( b.")
                                    .append(pairs.getKey()).append(" as text)) ")
                                    .append(operadorComparacion + " '" + signo);
                            where.append(valorAComparar.toUpperCase()).append(
                                     finalizacion + "' ");

                        }
                    } catch (SecurityException ex) {
//                    log.error("Error en generic", ex);
                    }
                }

            }
            System.err.println(" fillerAllAdd x " + fillerAllAdd + " where " + where);
            if (fillerAllAdd) {
                where.append(" AND 1=1 AND (");
                whereAdded = false;
                for (Map.Entry<String, FilterMeta> pairs : filters.entrySet()) {

                    if (whereAdded == true) {
                        where.append(" OR ");
                    }
                    try {
                        try {
                            field = getClase().getDeclaredField(pairs.getKey());
                        } catch (NoSuchFieldException ex) {
//                        log.error("error", ex);
                            int inicio = pairs.getKey().indexOf(".");
                            // StringTokenizer tokens=new
                            // StringTokenizer(pairs.getKey(),".");
                            if (inicio > 0) {
                                field = obtieneField(
                                        pairs.getKey().substring(0, inicio),
                                        pairs.getKey().substring(inicio + 1,
                                                pairs.getKey().length()),
                                        getClase());
                            }
                        } catch (Exception e) {
//                        log.error(e);

                        }
                        Class type = field.getType();
//                    log.debug("Clase Type.......................... = " + type);
                        if (Number.class.isAssignableFrom(type)) {
                            where.append("b.").append(pairs.getKey()).append(" = ");
                            where.append(filterAllValue);
                            whereAdded = true;
                        } else if (String.class.isAssignableFrom(type)) {
                            String signo = "";

                            /*Para buscar por la palabra que comienza es por defecto
                         Para buscar por la palabra que contiene el filtro debe
                         empezar con el signo %*/
                            signo = "%";
                            finalizacion = "%";
                            operadorComparacion = "LIKE";
                            //valorAComparar = pairs.getValue().toString();
                            valorAComparar = pairs.getValue().getFilterValue().toString();
                            if ((pairs.getValue().getFilterValue().toString().indexOf("=")) != -1) {
                                System.err.println("***************ENTREEEE");
                                finalizacion = "";
                                operadorComparacion = "=";
                                //pairs.setValue(pairs.getValue().toString().substring(0,1)); //cadena_c.substring(0,5);
                                valorAComparar = pairs.getValue().getFilterValue().toString().substring(1, valorAComparar.length());
                                signo = "";
                            }
                            System.err.println("***************NO ENTRE");
//                            if ((pairs.getKey().indexOf("*")) != -1) {
//                                finalizacion = "%";
//                                signo="%";
//                            }
                            where.append("UPPER(").append("b.")
                                    .append(pairs.getKey()).append(")")
                                    .append(operadorComparacion + " '" + signo);
                            where.append(valorAComparar.toUpperCase()).append(
                                    finalizacion + "' ");
                            whereAdded = true;
                            whereAdded = true;
                        }
                    } catch (SecurityException ex) {
//                    log.error("Error en generic", ex);
                    }

                }
                where.append(")");

            }

            whereOrden = new StringBuilder(where);

            if (groupByAdded) {
                where.append(" group by ").append(campoAgrupacion);
            }

            //  TODO sort genericLazyListNew
            if (sort.size() > 0) {
                Map.Entry sortEntry = sort.entrySet().stream().findFirst().get();
                log.trace("sort entry", sortEntry);
                SortMeta sortMeta = (SortMeta) sortEntry.getValue();
                String sortField = sortMeta.getField();
                SortOrder sortOrder = sortMeta.getOrder();
                if (orderByAdded && (sortField == null || sortField.isEmpty())) {
                    where.append(" ORDER BY b.").append(campoOrdenacion).append(orden);
                } else if (sortField != null && !sortField.isEmpty()) {
                    where.append(" ORDER BY b.").append(sortField).append(sortOrder != null && sortOrder.equals(SortOrder.ASCENDING) ? " ASC" : " DESC");
                }
            } else if (orderByAdded) {
                where.append(" ORDER BY b.").append(campoOrdenacion).append(orden);
            }

            String sCountAux = queryCount.toString();
            String s = query.toString();
            String s2 = where.toString();
            String sOrden = whereOrden.toString();

            if (whereAdded) {
                s3 = s + s2;
                sCount = sCountAux + sOrden;
            } else {
                s3 = s;
                sCount = sCountAux;
            }
//            log.debug("SQL............. " + s3);
            System.out.println("Consulta generic " + s3);
            javax.persistence.Query q = em.createQuery(s3, getClase());
            //  System.err.println("*********QUERY "+sCount+ " cantidad ");

            TypedQuery<Long> qCount = em.createQuery(sCount, Long.class);
            Long countRow = qCount.getSingleResult();
            if (sumaAdded) {
                javax.persistence.Query qSuma = em.createQuery("select " + campoSuma + " FROM "
                        + getClase().getSimpleName() + (filtrosVistas != null ? filtrosVistas : "") + " b " + sOrden, getClase());
                if (cantCampoSuma <= 1) {
                    setSumaTotal((BigDecimal) qSuma.getSingleResult());
                } else {
                    setObjetosSumas((Object[]) qSuma.getSingleResult());
                }
            }
            // System.out.println("Cant Reg " + countRow);
            this.queryActual = s3;
            this.whereActual = sOrden;
            this.setRowCount(countRow.intValue());
            q.setFirstResult(firstResult);
            q.setMaxResults(pageSize);
            lazyList = q.getResultList();
            // this.setRowCount(lazyList.size());
        } catch (Exception e) {
            log.error("Error en generciList " + s3, e);
        }
        return lazyList;

    }

    public List getListaActual() {

        try {
            javax.persistence.Query q = em.createQuery(queryActual, getClase());

            return q.getResultList();
        } catch (Exception e) {
//            log.error("Error en generciList " + queryActual, e);

        }
        return null;
    }

    public String obtieneWhereActual() {
        return whereActual;
    }

    private Map<String, Object> getCamposMap() {
        return camposMap;
    }

    private void setCamposMap(Map<String, Object> campos) {
        this.camposMap = campos;
    }

    private void setClase(Class c) {
        clase = c;
    }

    private Class<T> getClase() {
        return clase;
    }

    @Remove
    public void destruir() {
    }

    /**
     * @return the sumaTotal
     */
    public BigDecimal getSumaTotal() {
        return sumaTotal;
    }

    /**
     * @param sumaTotal the sumaTotal to set
     */
    public void setSumaTotal(BigDecimal sumaTotal) {
        this.sumaTotal = sumaTotal;
    }

    /**
     * @return the queryActual
     */
    public String getQueryActual() {
        return queryActual;
    }

    /**
     * @param queryActual the queryActual to set
     */
    public void setQueryActual(String queryActual) {
        this.queryActual = queryActual;
    }

    /**
     * @return the whereActual
     */
    public String getWhereActual() {
        return whereActual;
    }

    /**
     * @param whereActual the whereActual to set
     */
    public void setWhereActual(String whereActual) {
        this.whereActual = whereActual;
    }

    /**
     * @return the camposFiltrosAll
     */
    public Map<String, Object> getCamposFiltrosAll() {
        return camposFiltrosAll;
    }

    /**
     * @param camposFiltrosAll the camposFiltrosAll to set
     */
    public void setCamposFiltrosAll(Map<String, Object> camposFiltrosAll) {
        this.camposFiltrosAll = camposFiltrosAll;
    }

    /**
     * @return the cantCampoSuma
     */
    public Integer getCantCampoSuma() {
        return cantCampoSuma;
    }

    /**
     * @param cantCampoSuma the cantCampoSuma to set
     */
    public void setCantCampoSuma(Integer cantCampoSuma) {
        this.cantCampoSuma = cantCampoSuma;
    }

    /**
     * @return the objetosSumas
     */
    public Object[] getObjetosSumas() {
        return objetosSumas;
    }

    /**
     * @param objetosSumas the objetosSumas to set
     */
    public void setObjetosSumas(Object[] objetosSumas) {
        this.objetosSumas = objetosSumas;
    }

    /**
     * @return the filtrosVistas
     */
    public String getFiltrosVistas() {
        return filtrosVistas;
    }

    /**
     * @param filtrosVistas the filtrosVistas to set
     */
    public void setFiltrosVistas(String filtrosVistas) {
        this.filtrosVistas = filtrosVistas;
    }
}
