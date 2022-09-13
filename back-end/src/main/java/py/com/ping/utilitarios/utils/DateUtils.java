/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.utilitarios.utils;

import py.com.ping.administracionBase.util.ApplicationConstant;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author rudy
 */
public class DateUtils {

    private static final SimpleDateFormat sdfYYYYMM = new SimpleDateFormat("yyyyMM");
    private static final SimpleDateFormat sdfYYYY = new SimpleDateFormat("yyyy");
    public static final String MARCACION_FORMAT = "dd/MM/yyyy HH:mm";
    public static final String DATE_STORE = "yyyyMMdd";

    public static Date getFechaStringToDate(String fecha) {
        if (fecha != null) {

            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date date = formatter.parse(fecha);
                return date;
            } catch (ParseException ex) {
                return null;
            }

        }

        return null;

    }

    public static String obtieneDia(int dia) {
        if (dia == 1) {
            return "Domingo";
        } else if (dia == 2) {
            return "Lunes";
        } else if (dia == 3) {
            return "Martes";
        } else if (dia == 4) {
            return "Miercoles";
        } else if (dia == 5) {
            return "Jueves";
        } else if (dia == 6) {
            return "Viernes";
        } else if (dia == 7) {
            return "Sabado";
        }
        return "";
    }

    public static Date parseDate(String fecha, String format) {
        if (fecha != null) {
            DateFormat formatter = new SimpleDateFormat(format);
            try {
                Date date = formatter.parse(fecha);
                return date;
            } catch (ParseException ex) {
                return null;
            }

        }
        return null;
    }

    public static Date getFechaStringToDate(String fecha, String formato) {
        if (fecha != null) {

            DateFormat formatter = new SimpleDateFormat(formato);
            try {
                Date date = formatter.parse(fecha);
                return date;
            } catch (ParseException ex) {
                return null;
            }

        }

        return null;

    }

    public static String getFechaFormateada(Date fecha) {
        if (fecha != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(fecha);
        } else {
            return null;
        }
    }

    /**
     * *
     * Si la fecha1 es menor a la fechaActual retorna false, sino retorna true
     */
    public static boolean comparaFecha(String fecha1, String fechaActual) {
        try {
            /**
             * Obtenemos las fechas enviadas en el formato a comparar
             */
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaDate1 = formateador.parse(fecha1);
            Date fechaDate2 = formateador.parse(fechaActual);

            if (fechaDate1.before(fechaDate2)) {
                return false;
            } else {
                return true;
            }
        } catch (ParseException e) {
            System.out.println("Se Produjo un Error!!!  " + e.getMessage());
        }
        return false;
    }

    public static String getFechaFormateada(Date fecha, String format) {
        if (fecha != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(fecha);
        } else {
            return null;
        }
    }

    public static String getFechaFormateadaValidNull(Date fecha, String format) {
        if (fecha == null) {
            fecha = new Date();
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(fecha);

    }

    public static String getFechaFormateadaMesCastellano(Date fecha, String format) {
        if (fecha != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Calendar cal = new GregorianCalendar();
            cal.setTime(fecha);
            int dia = cal.get(Calendar.DAY_OF_MONTH);
            String mes = getNombreMes(cal.get(Calendar.MONTH));
            int anio = cal.get(Calendar.YEAR);
            return dia + "-" + mes + "-" + anio;
        } else {
            return null;
        }
    }

    public static String getMesAño(Date fecha, String format) {
        if (fecha != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Calendar cal = new GregorianCalendar();
            cal.setTime(fecha);
            int mes = cal.get(Calendar.MONTH) + 1;
            int anio = cal.get(Calendar.YEAR);
            return mes + "" + anio;
        } else {
            return null;
        }
    }

    public static String getFechaMesAño(Date fecha) {
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
        return sf.format(fecha == null ? new Date() : fecha);
    }

    public static String getNombreMes(Date fecha) {
        if (fecha != null) {
            Calendar cal = new GregorianCalendar();
            cal.setTime(fecha);
            int dia = cal.get(Calendar.DAY_OF_MONTH);
            String mes = getNombreMes(cal.get(Calendar.MONTH));
            int anio = cal.get(Calendar.YEAR);
            return mes + "-" + anio;
        } else {
            return null;
        }
    }

    public static String getNombreMes(int mes) {
        mes = mes + 1;
        String mesString;
        switch (mes) {
            case 1:
                mesString = "ENERO";
                break;
            case 2:
                mesString = "FEBRERO";
                break;
            case 3:
                mesString = "MARZO";
                break;
            case 4:
                mesString = "ABRIL";
                break;
            case 5:
                mesString = "MAYO";
                break;
            case 6:
                mesString = "JUNIO";
                break;
            case 7:
                mesString = "JULIO";
                break;
            case 8:
                mesString = "AGOSTO";
                break;
            case 9:
                mesString = "SEPTIEMBRE";
                break;
            case 10:
                mesString = "OCTUBRE";
                break;
            case 11:
                mesString = "NOVIEMBRE";
                break;
            case 12:
                mesString = "DICIEMBRE";
                break;
            default:
                mesString = "Invalid month";
                break;
        }
        System.out.println(mesString);
        return mesString;
    }

    public static String getFechaAsyyyyMM(Integer anio, Integer mes) {

        Calendar fecha = Calendar.getInstance();
        fecha.set(anio, mes - 1, 1);
        return sdfYYYYMM.format(fecha.getTime());

    }

    public static String getFechaActualAsYYYY() {
        return sdfYYYY.format(new Date());

    }

    public static String paddedWitchLeftZero(Integer number, Integer espacios) {
        return String.format("%0" + espacios + "d", number);

    }

    public static int diffMeses(Date fechaIni, Date fechaFin) {

        Calendar inicio = new GregorianCalendar();
        Calendar fin = new GregorianCalendar();
        inicio.setTime(fechaIni != null ? fechaIni : new Date());
        fin.setTime(fechaFin != null ? fechaFin : new Date());
        int difA = fin.get(Calendar.YEAR) - inicio.get(Calendar.YEAR);
        int difM = difA * 12 + fin.get(Calendar.MONTH) - inicio.get(Calendar.MONTH);

        return (difM > 0) ? difM : 1;
    }

    public static Date restarMeses(Date fecha, int mes) {
        Calendar cal = dateToCalendar((fecha));
        cal.add(Calendar.MONTH, mes);
        return cal.getTime();
    }

    public static Date sumarMeses(Date fecha, int mes) {
        Calendar cal = dateToCalendar((fecha));
        cal.add(Calendar.MONTH, mes);
        return cal.getTime();
    }

    public static Date sumarDias(Date fecha, int dias) {
        Calendar cal = dateToCalendar(fecha);
        cal.add(Calendar.DAY_OF_YEAR, dias);
        System.out.println("" + cal.getTime());
        return cal.getTime();
    }

    public static Long diferenciaDias(Date fecha, Date fechaHasta) {
        Long dt2 = (fechaHasta != null ? fechaHasta : new Date()).getTime();
        Long diff = dt2 - (fecha != null ? fecha : new Date()).getTime();
        Long diffSeconds = diff / 1000 % 60;
        //System.out.println(" Segundos: " + diffSeconds);
        Long diffMinutes = diff / (60 * 1000) % 60;
        //System.out.println(" Minutos: " + diffMinutes);
        Long diffHours = diff / (60 * 60 * 1000) % 24;
        //System.out.println(" Hora: " + diffHours);
        Long diffInDays = diff / (1000 * 60 * 60 * 24);
        System.out.println(" Dias: " + diffInDays);
        return diffInDays;
    }

    public static Calendar dateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static Date getPrimerDiaDelMes(Date fecha) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);

        cal.set(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.getActualMinimum(Calendar.DAY_OF_MONTH),
                cal.getMinimum(Calendar.HOUR_OF_DAY),
                cal.getMinimum(Calendar.MINUTE),
                cal.getMinimum(Calendar.SECOND));

        return cal.getTime();

    }

    public static int getMes(Date fecha) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);

        return cal.get(Calendar.MONTH);

    }

    public static int getAnho(Date fecha) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);

        return cal.get(Calendar.YEAR);

    }

//    public static String getNombreMes(int mes){
//        mes=mes+1;
//    String mesString;
//    switch (mes) {
//        case 1:  mesString = "Enero";
//                 break;
//        case 2:  mesString  = "Febrero";
//                 break;
//        case 3:  mesString = "Marzo";
//                 break;
//        case 4:  mesString = "Abril";
//                 break;
//        case 5:  mesString = "Mayo";
//                 break;
//        case 6:  mesString = "Junio";
//                 break;
//        case 7:  mesString = "Julio";
//                 break;
//        case 8:  mesString = "Agosto";
//                 break;
//        case 9:  mesString = "Septiembre";
//                 break;
//        case 10: mesString = "Octubre";
//                 break;
//        case 11: mesString = "Noviembre";
//                 break;
//        case 12: mesString = "Diciembre";
//                 break;
//        default: mesString = "Invalid month";
//                 break;
//        }
//        System.out.println(mesString);
//        return mesString;
//    }
    public static Date getUltimoDiaDelMes(Date fecha) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.set(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.getActualMaximum(Calendar.DAY_OF_MONTH),
                cal.getMaximum(Calendar.HOUR_OF_DAY),
                cal.getMaximum(Calendar.MINUTE),
                cal.getMaximum(Calendar.SECOND));

        return cal.getTime();

    }

    public static String fechaAsString(Date fecha) {
        if (fecha == null) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);
            builder.append(cal.get(Calendar.DAY_OF_MONTH)).append(" ");
            builder.append(ApplicationConstant.MESES.get(cal.get(Calendar.MONTH))).append(" ");
            builder.append(cal.get(Calendar.YEAR));
            return builder.toString();
        }

    }

    /**
     * Devuelve ej: VIERNES 07 DE MARZO DE 2016 hasta las 16:00 horas
     */
    public static String fechaHoraLimite(Date fecha) {
        if (fecha == null) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);
            int numeroDia = cal.get(Calendar.DAY_OF_WEEK);
            builder.append(ApplicationConstant.DIAS.get(numeroDia - 1)).append(" ");
            builder.append(cal.get(Calendar.DAY_OF_MONTH)).append(" de ");
            builder.append(ApplicationConstant.MESES.get(cal.get(Calendar.MONTH))).append(" de ");
            builder.append(cal.get(Calendar.YEAR));
            builder.append(" hasta las ");
            builder.append(cal.get(Calendar.HOUR_OF_DAY)).append(":").append(String.format("%02d", cal.get(Calendar.MINUTE))).append(" hs.");
            return builder.toString();
        }
    }

    /**
     * Devuelve ej: VIERNES 07 DE MARZO DE 2016 a las 16:00 horas
     */
    public static String fechaHoraAlas(Date fecha) {
        if (fecha == null) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);
            int numeroDia = cal.get(Calendar.DAY_OF_WEEK);
            builder.append(ApplicationConstant.DIAS.get(numeroDia - 1)).append(" ");
            builder.append(cal.get(Calendar.DAY_OF_MONTH)).append(" de ");
            builder.append(ApplicationConstant.MESES.get(cal.get(Calendar.MONTH))).append(" de ");
            builder.append(cal.get(Calendar.YEAR));
            builder.append(" a las ");
            builder.append(cal.get(Calendar.HOUR_OF_DAY)).append(":").append(String.format("%02d", cal.get(Calendar.MINUTE))).append(" horas");
            return builder.toString();
        }
    }

    /**
     * Devuelve ej: VIERNES 07 DE MARZO DE 2016 - 16:00 HS.
     */
    public static String diaFechaHora(Date fecha) {
        if (fecha == null) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);
            int numeroDia = cal.get(Calendar.DAY_OF_WEEK);
            builder.append(ApplicationConstant.DIAS.get(numeroDia - 1)).append(" ");
            builder.append(cal.get(Calendar.DAY_OF_MONTH)).append(" de ");
            builder.append(ApplicationConstant.MESES.get(cal.get(Calendar.MONTH))).append(" de ");
            builder.append(cal.get(Calendar.YEAR));
            builder.append(" - ");
            builder.append(cal.get(Calendar.HOUR_OF_DAY)).append(":").append(String.format("%02d", cal.get(Calendar.MINUTE))).append("  hs.");
            return builder.toString();
        }
    }

    /**
     * Devuelve ej: VIERNES 07 DE MARZO DE 2016.
     */
    public static String diaFecha(Date fecha) {
        if (fecha == null) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);
            int numeroDia = cal.get(Calendar.DAY_OF_WEEK);
            builder.append(ApplicationConstant.DIAS.get(numeroDia - 1)).append(" ");
            builder.append(cal.get(Calendar.DAY_OF_MONTH)).append(" de ");
            builder.append(ApplicationConstant.MESES.get(cal.get(Calendar.MONTH))).append(" de ");
            builder.append(cal.get(Calendar.YEAR));
            return builder.toString();
        }
    }

    /**
     * Devuelve ej: Asunción, 00 de febrero de 2016
     */
    public static String fechaAsuncion(Date fecha) {
        if (fecha == null) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);
            builder.append("Asunción, ");
            builder.append(cal.get(Calendar.DAY_OF_MONTH)).append(" de ");
            builder.append(ApplicationConstant.MESES.get(cal.get(Calendar.MONTH))).append(" de ");
            builder.append(cal.get(Calendar.YEAR));
            return builder.toString();
        }
    }

    /**
     * Devuelve ej: 00 DE MARZO DE 2016 - 17:00 Hs
     */
    public static String fechaHora(Date fecha) {
        if (fecha == null) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);
            builder.append(cal.get(Calendar.DAY_OF_MONTH)).append(" de ");
            builder.append(ApplicationConstant.MESES.get(cal.get(Calendar.MONTH))).append(" de ");
            builder.append(cal.get(Calendar.YEAR)).append(" - ");
            builder.append(cal.get(Calendar.HOUR_OF_DAY)).append(":").append(String.format("%02d", cal.get(Calendar.MINUTE))).append(" hs");
            return builder.toString();
        }
    }

    /**
     * Devuelve ej: SETIEMBRE /2016
     */
    public static String getAsMesBarraAnio(Date fecha) {
        if (fecha == null) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);
            builder.append(ApplicationConstant.MESES.get(cal.get(Calendar.MONTH))).append(" / ");
            builder.append(cal.get(Calendar.YEAR));
            return builder.toString();
        }
    }

    /**
     * Devuelve ej: 09:00 hs. del día 14 de Octubre de 2016
     */
    public static String horaFecha(Date fecha) {
        if (fecha == null) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);
            //int numeroDia = cal.get(Calendar.DAY_OF_WEEK);
            builder.append(cal.get(Calendar.HOUR_OF_DAY)).append(":").append(String.format("%02d", cal.get(Calendar.MINUTE))).append("  hs. del día ");
            //builder.append(ApplicationConstant.DIAS.get(numeroDia - 1)).append(" ");
            builder.append(cal.get(Calendar.DAY_OF_MONTH)).append(" de ");
            builder.append(ApplicationConstant.MESES.get(cal.get(Calendar.MONTH))).append(" de ");
            builder.append(cal.get(Calendar.YEAR));
            return builder.toString();
        }
    }

    public static String cantidadDiasHastaHoy(Date fecha) {
        StringBuilder sb = new StringBuilder();
        Long dt2 = (new Date()).getTime();
        Long diff = dt2 - fecha.getTime();
        Long diffSeconds = diff / 1000 % 60;
        //System.out.println(" Segundos: " + diffSeconds);
        Long diffMinutes = diff / (60 * 1000) % 60;
        //System.out.println(" Minutos: " + diffMinutes);
        Long diffHours = diff / (60 * 60 * 1000) % 24;
        //System.out.println(" Hora: " + diffHours);
        Long diffInDays = diff / (1000 * 60 * 60 * 24);
        //System.out.println(" Dias: " + diffInDays);
        sb.append(diffInDays).append(" dias ").append(diffHours)
                .append(" horas ").append(diffMinutes).append(" minutos ");
        return sb.toString();
    }

    public static Long diferenciaDias(Date fecha) {
        Long dt2 = (new Date()).getTime();
        Long diff = dt2 - fecha.getTime();
        Long diffSeconds = diff / 1000 % 60;
        //System.out.println(" Segundos: " + diffSeconds);
        Long diffMinutes = diff / (60 * 1000) % 60;
        //System.out.println(" Minutos: " + diffMinutes);
        Long diffHours = diff / (60 * 60 * 1000) % 24;
        //System.out.println(" Hora: " + diffHours);
        Long diffInDays = diff / (1000 * 60 * 60 * 24);
        //System.out.println(" Dias: " + diffInDays);
        return diffInDays;
    }

    public static String cantidadSoloDiasHastaHoy(Date fecha) {
        StringBuilder sb = new StringBuilder();

        sb.append(diferenciaDias(fecha)).append(" dias ");
        return sb.toString();
    }

    public static String cantidadSoloDiasHastaHoyNoNegativo(Date fecha) {
        StringBuilder sb = new StringBuilder();
        Long dif = diferenciaDias(fecha);
        if (dif > 0) {
            sb.append(dif).append(" dias ");
            return sb.toString();
        } else {
            return "-";
        }
    }

    public static String cantidadSoloDiasHastaHoyNegativo(Date fecha) {
        StringBuilder sb = new StringBuilder();
        Long dif = diferenciaDias(fecha);
        if (dif < 0) {
            sb.append(dif * -1).append(" dias ");
            return sb.toString();
        } else {
            return "-";
        }
    }

    public static Period getAntiguedad(Date ini, Date fin) {

        LocalDate iniDate = ini.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate finDate = fin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Period period = Period.between(iniDate, finDate);
//        int years = period.getYears();
//        int months = period.getMonths();
//        int days = period.getDays();

        return period;
    }

    //retorna la diferencia en minutos entre 2 fechas
    public static long diffMinEntreFechas(Date iniDate, Date finDate) {
        long diff = diffMiliSecondEntreFechas(iniDate, finDate);
        try {
            return Math.abs(diff / (60 * 1000));
        } catch (Exception e) {
            return Long.valueOf(0);
        }

    }

    public static long diffMiliSecondEntreFechas(Date iniDate, Date finDate) {
        Calendar ini = Calendar.getInstance();
        ini.setTime(iniDate);
        Calendar fin = Calendar.getInstance();
        fin.setTime(finDate);
        long diff = ini.getTimeInMillis() - fin.getTimeInMillis();
        return diff;
    }

    public static void main(String[] args) {
        System.out.println(fechaHoraLimite(new Date()));
        Calendar ini = Calendar.getInstance();
        ini.set(Calendar.YEAR, 2005);
        ini.set(Calendar.MONTH, 7);
        ini.set(Calendar.DAY_OF_MONTH, 3);
        System.out.println(getAntiguedad(ini.getTime(), new Date()));
    }

    public static String getHoraFormateada(Date dateTime) {
        if (dateTime != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            return sdf.format(dateTime);
        } else {
            return null;
        }
    }

    public static Date convertirStringADate(String fechaString, String formato) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat(formato);
        Date fecha = null;
        try {
            fecha = formatoDelTexto.parse(fechaString);

        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return fecha;
    }

    public static Date convertirStringADate(String fechaString) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null;
        try {
            fecha = formatoDelTexto.parse(fechaString);

        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return fecha;
    }

    public static String getDiaAnteriorAsyyyyMMdd(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (fecha != null && !fecha.isEmpty()) {

            try {
                Date fechaActual = sdf.parse(fecha);
                Calendar calendar = restarUnDia(fechaActual);
                return sdf.format(calendar.getTime());
            } catch (Exception ex) {
                return null;
            }
        } else {
//            Calendar calendar = restarUnDia(new Date());
//            return sdf.format(calendar.getTime());
            //Cuando se instala recien tiene que descargar todos los saldos           
            return sdf.format(getOrigenTiempo());
        }
    }

    private static Date getOrigenTiempo() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1981);
        calendar.set(Calendar.MONTH, 5);
        calendar.set(Calendar.DAY_OF_MONTH, 29);
        return calendar.getTime();
    }

    public static Calendar restarUnDia(Date fechaActual) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return calendar;
    }

    public static String cantidadMinutosDiferencias(Date fecha1, Date fecha2) {
        if (fecha1 != null && fecha2 != null) {
            StringBuilder sb = new StringBuilder();
            Long dt2 = (fecha1).getTime();
            Long diff = dt2 - fecha2.getTime();
            Long diffSeconds = diff / 1000 % 60;
            //System.out.println(" Segundos: " + diffSeconds);
            Long diffMinutes = diff / (60 * 1000) % 60;
            //System.out.println(" Minutos: " + diffMinutes);
            Long diffHours = diff / (60 * 60 * 1000) % 24;
            //System.out.println(" Hora: " + diffHours);
            Long diffInDays = diff / (1000 * 60 * 60 * 24);
            //System.out.println(" Dias: " + diffInDays);
            Long diffMeses = (diff / (1000 * 60 * 60 * 24)) / 30;
            System.out.println(" Meses: " + diffMeses);
            if (diffInDays > 0) {
                sb.append(diffInDays).append(" dias ");
            } else if (diffHours > 0) {
                sb.append(diffHours)
                        .append(" horas ").append(diffMinutes).append(" min. ").append(diffSeconds).append(" seg.");
            } else if (diffMinutes > 0) {
                sb.append(diffMinutes).append(" min. ").append(diffSeconds).append(" seg.");

            } else {
                sb.append(diffSeconds).append(" seg.");

            }
            return sb.toString();
        } else {
            return "-";
        }

    }

    /**
     * Devuelve ej: VIERNES 07 DE MARZO DE 2016.
     */
    public static String getDia(Date fecha) {
        if (fecha == null) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);
            builder.append(cal.get(Calendar.DAY_OF_MONTH));
            return builder.toString();
        }
    }

    public static Date getPrimerDiaDelAnho(Date fecha) {
        Calendar cal = Calendar.getInstance();
        Calendar calFecha = Calendar.getInstance();
        calFecha.setTime(fecha);
        cal.set(calFecha.get(Calendar.YEAR),
                0,
                cal.getActualMinimum(Calendar.DAY_OF_MONTH),
                cal.getMinimum(Calendar.HOUR_OF_DAY),
                cal.getMinimum(Calendar.MINUTE),
                cal.getMinimum(Calendar.SECOND));
        System.err.println("getPrimerDiaDelAnho" + cal.getTime());
        return cal.getTime();

    }

    public static Date restarDias(Date fecha, int dias) {
        Calendar cal = dateToCalendar(fecha);
        cal.add(Calendar.DAY_OF_YEAR, dias * -1);
        return cal.getTime();
    }

    public static LocalDateTime convertToLocalDateTime(Date fecha) {
        LocalDateTime localDateTime = LocalDateTime.from(Instant.ofEpochMilli(fecha.getTime()));
        return localDateTime;
    }

    public static Date convertToDate(LocalDateTime dateToConvert) {
        ZonedDateTime zonedDateTime = dateToConvert.atZone(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }
}
