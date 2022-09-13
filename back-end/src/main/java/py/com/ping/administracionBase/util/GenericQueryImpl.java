package py.com.ping.administracionBase.util;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import py.com.ping.utilitarios.interfaces.GenericQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author Guillermo Dominguez
 */
@Stateless
public class GenericQueryImpl implements GenericQuery {

    private Integer n;
    private Integer registroInicial;
   
    @PersistenceContext
    private EntityManager em;

    @PreDestroy
    public void cleanup() {
        if (getEm().isOpen()) {
            getEm().close();
        }
    }

    // @Override
    @Override
    public <T> List getSearchResultList(Map<String, Object> campos, Class<T> clase) {
        //Class clase=obj.getClass();
        StringBuilder query = new StringBuilder("SELECT b FROM "
                + clase.getSimpleName() + " b");
        StringBuilder where = null;
        boolean whereAdded = false;
        boolean addAdded = false;
        String s3, sCount;
        s3 = "";
        boolean orderByAdded = false;
        String campoOrdenacion = "";
        String orden = "";
        where = new StringBuilder(" WHERE ");
        Field field = null;
        List<T> lazyList = null;

        final Logger log = LoggerFactory.getLogger(GenericQueryImpl.class);


        try {

            for (Map.Entry<String, Object> pairs : campos.entrySet()) {
                // System.out.print("Campo Where lazy "+wh);
                log.debug("Key Campos.......................... = "
                        + pairs.getKey());

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

                    } else {
                        where.append("b.").append(operador[1])
                                .append(operador[2]);
                        where.append(pairs.getValue());
                    }
                } else {
                    where.append("b.").append(pairs.getKey()).append(" = '");
                    where.append(pairs.getValue() != null ? pairs.getValue().toString().toUpperCase() : null)
                            .append("'");
                }
                whereAdded = true;
            }
            if (orderByAdded) {
                where.append(" ORDER BY b.").append(campoOrdenacion).append(orden);
            }
            String s = query.toString();
            String s2 = where.toString();
            if (whereAdded == true) {
                s3 = s + s2;
            } else {
                s3 = s;
            }
            log.debug("SQL............. " + s3);
         //  System.out.println("Consulta generic " + s3 + " em " + getEm()+" registroInicial "+registroInicial+" n "+n);
            javax.persistence.Query q;

            if (n != null && n != 0 && registroInicial!=null && registroInicial!=0) {
                  q = getEm().createQuery(s3, clase).setFirstResult(registroInicial).setMaxResults(n);
            } else if(n != null && n != 0) {
                  q = getEm().createQuery(s3, clase).setMaxResults(n);
            } else if(registroInicial!=null && registroInicial!=0){
                  q = getEm().createQuery(s3, clase).setFirstResult(registroInicial);
            } else {
                   q = getEm().createQuery(s3, clase);
            }
            
           
            lazyList = q.getResultList();
            // this.setRowCount(lazyList.size());
        } catch (Exception e) {
            log.error("Error en generciList " + s3, e);

        } /*
         * finally { if (em != null) { em.close(); } }
         */

        return lazyList;
    }

    @Override
    public <T> List getSearchResultList(Map<String, Object> campos, Class<T> clase, String paramSelect) {
	//	Class clase=obj.getClass();
		if(paramSelect==null || paramSelect.isEmpty())
                    paramSelect="b";
		StringBuilder query = new StringBuilder("SELECT "+paramSelect+" FROM "
				+ clase.getSimpleName() + " b");
     
        StringBuilder where = null;
        boolean whereAdded = false;
        boolean addAdded = false;
        String s3, sCount;
        s3 = "";
        boolean orderByAdded = false;
        String campoOrdenacion = "";
        String orden = "";
        where = new StringBuilder(" WHERE ");
        Field field = null;
        List<T> lazyList = null;
        final Logger log = LoggerFactory.getLogger(GenericQueryImpl.class);

        try {

            for (Map.Entry<String, Object> pairs : campos.entrySet()) {
                // System.out.print("Campo Where lazy "+wh);
                log.debug("Key Campos.......................... = "
                        + pairs.getKey());

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

                    } else {
                        where.append("b.").append(operador[1])
                                .append(operador[2]);
                        where.append(pairs.getValue());
                    }
                } else {
                    where.append("b.").append(pairs.getKey()).append(" = '");
                    where.append(pairs.getValue() != null ? pairs.getValue().toString().toUpperCase() : null)
                            .append("'");
                }
                whereAdded = true;
            }
            if (orderByAdded) {
                where.append(" ORDER BY b.").append(campoOrdenacion).append(orden);
            }
            String s = query.toString();
            String s2 = where.toString();
            if (whereAdded == true) {
                s3 = s + s2;
            } else {
                s3 = s;
            }
            log.debug("SQL............. " + s3);
          //  System.out.println("Consulta generic " + s3 + " em " + getEm()+" registroInicial "+registroInicial+" n "+n);
            javax.persistence.Query q;

            if (n != null && n != 0 && registroInicial!=null && registroInicial!=0) {
                  q = getEm().createQuery(s3, clase).setFirstResult(registroInicial).setMaxResults(n);
            } else if(n != null && n != 0) {
                  q = getEm().createQuery(s3, clase).setMaxResults(n);
            } else if(registroInicial!=null && registroInicial!=0){
                  q = getEm().createQuery(s3, clase).setFirstResult(registroInicial);
            } else {
                   q = getEm().createQuery(s3, clase);
            }
            
           
            lazyList = q.getResultList();
            // this.setRowCount(lazyList.size());
        } catch (Exception e) {
            log.error("Error en generciList " + s3, e);

        } /*
         * finally { if (em != null) { em.close(); } }
         */

        return lazyList;
    }

  	@Override
	public <T>Object getSearchSingleResult(Map<String, Object> campos, Class<T> clase, String paramSelect) {
	//	Class clase=obj.getClass();
		if(paramSelect==null || paramSelect.isEmpty())
                    paramSelect="b";
		StringBuilder query = new StringBuilder("SELECT "+paramSelect+" FROM "
				+ clase.getSimpleName() + " b");
		StringBuilder where = null;
		boolean whereAdded = false;
		boolean addAdded=false;
		String s3, sCount;
		s3="";
		boolean orderByAdded = false; 
        String campoOrdenacion="";
        String orden="";
		where = new StringBuilder(" WHERE ");
		Field field = null;
		T result=null;
        final Logger log = LoggerFactory.getLogger(GenericQueryImpl.class);
		
		try {

			for (Map.Entry<String, Object> pairs : campos.entrySet()) {
				// System.out.print("Campo Where lazy "+wh);
				log.debug("Key Campos.......................... = "
						+ pairs.getKey());

				if (pairs.getKey().equalsIgnoreCase("JOIN")) {
					where = new StringBuilder(" ");
					where.append(pairs.getValue());
					//where.append(" WHERE ");
					//whereAdded = false;
					whereAdded=true;
					continue;
				}
				addAdded=false;
				if (whereAdded == true) {
					where.append(" AND ");
					addAdded=true;
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
				 * siguiente formato:
				 * campos.put("-1.operador:estado: not like ", "'B'"); el valor
				 * "-1.operador:" se pone para acceder a esta opcion;
				 * acontinuacion del ":" se especifica el campo(en este ejemplo:
				 * estado) y luego de ":" operador relacional deseado (en este
				 * ejemplo: not like)
				 **/
				else if ((pairs.getKey().indexOf("-1.operador:")) != -1) {
					String[] operador = pairs.getKey().split(":");
					if (operador[2].indexOf("OR") != -1) {
						where.append("(b.").append(operador[1]).append(" OR ")
								.append("b.").append(pairs.getValue())
								.append(")");
					} else if (operador[2].indexOf("BY") != -1) {
							orderByAdded=true;
						    campoOrdenacion=operador[1];
	                        orden=pairs.getValue().toString();
	                        if(addAdded)
	                        	where.append("1=1");
	                        continue;

					}  else if (operador[2].indexOf("LIKE") != -1) {
					
						where.append("upper(b.").append(operador[1]).append(")").append(operador[2]);
						where.append(pairs.getValue());

					} else {
						where.append("b.").append(operador[1])
								.append(operador[2]);
						where.append(pairs.getValue());
					}
				} else {
					where.append("b.").append(pairs.getKey()).append(" = '");
					where.append(pairs.getValue()!=null?pairs.getValue().toString():null)
							.append("'");
				}
				whereAdded = true;
			}
			   if(orderByAdded)
                            where.append(" ORDER BY b.").append(campoOrdenacion).append(orden);
			String s = query.toString();
			String s2 = where.toString();
			if (whereAdded == true) {
				s3 = s + s2;
			} else {
				s3 = s;
			}
			log.debug("SQL............. " + s3);
			//System.out.println("Consulta generic SINGLE RESULT" + s3);
			javax.persistence.Query q = getEm().createQuery(s3, clase);
			
			
			
			
			
			// setMaxResult puse por que existen descripciones duplicadas
			result =  (T) q.setMaxResults(1).getSingleResult();
                  	
			// this.setRowCount(lazyList.size());
		} catch (Exception e) {
			log.error("Error en generciList "+s3,e);

		}
			/*
		 * finally { if (em != null) { em.close(); } }
		 */
		return result;
	}

    
     @Override
    public <T> Long getSearchCount(Map<String, Object> campos, Class<T> clase) {
         StringBuilder query = new StringBuilder("SELECT count(b) FROM "
                + clase.getSimpleName() + " b");
        StringBuilder where = null;
        boolean whereAdded = false;
        boolean addAdded = false;
        String s3, sCount;
        s3 = "";
        boolean orderByAdded = false;
        String campoOrdenacion = "";
        String orden = "";
        where = new StringBuilder(" WHERE ");
        Field field = null;
        Long result = null;
         final Logger log = LoggerFactory.getLogger(GenericQueryImpl.class);

        try {

            for (Map.Entry<String, Object> pairs : campos.entrySet()) {
                // System.out.print("Campo Where lazy "+wh);
                log.debug("Key Campos.......................... = "
                        + pairs.getKey());

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

                    } else {
                        where.append("b.").append(operador[1])
                                .append(operador[2]);
                        where.append(pairs.getValue());
                    }
                } else {
                    where.append("b.").append(pairs.getKey()).append(" = '");
                    where.append(pairs.getValue() != null ? pairs.getValue().toString() : null)
                            .append("'");
                }
                whereAdded = true;
            }
//            if (orderByAdded) {
//                where.append(" ORDER BY b.").append(campoOrdenacion).append(orden);
//            }
            String s = query.toString();
            String s2 = where.toString();
            if (whereAdded == true) {
                s3 = s + s2;
            } else {
                s3 = s;
            }
            log.debug("SQL............. " + s3);
          //  System.out.println("Consulta generic SINGLE RESULT" + s3);
            javax.persistence.Query q = getEm().createQuery(s3, clase);

            // setMaxResult puse por que existen descripciones duplicadas
            result =  (Long) q.getSingleResult();

            // this.setRowCount(lazyList.size());
        } catch (Exception e) {
            log.error("Error en generciList " + s3, e);

        }
        /*
         * finally { if (em != null) { em.close(); } }
         */
        return result;
    
    }
    /**
     * @return the em
     */
    public EntityManager getEm() {
        return em;
    }

    /**
     * @param em the em to set
     */
    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public void setCantidadReg(Integer n) {
        this.n = n;
    }

    @Override
    public void setRegistroInicial(Integer registroInicial) {
        this.registroInicial=registroInicial;
    }

   

}
