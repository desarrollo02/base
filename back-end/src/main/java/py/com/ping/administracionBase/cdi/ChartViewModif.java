package py.com.ping.administracionBase.cdi;
 
import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean
public class ChartViewModif implements Serializable {
 
    private LineChartModel animatedModel1;
    private BarChartModel animatedModel2;
    
    @PostConstruct
    public void init() {
        createAnimatedModels();
    }
 
    public LineChartModel getAnimatedModel1() {
        return animatedModel1;
    }
 
    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }
 
    private void createAnimatedModels() {
        animatedModel1 = initLinearModel();
        animatedModel1.setTitle("Presupuesto Mensual");
        animatedModel1.setAnimate(true);
        animatedModel1.setShowPointLabels(true);
        animatedModel1.setLegendPosition("se");
        animatedModel1.getAxes().put(AxisType.X, new CategoryAxis("Meses"));
        Axis xAxis=animatedModel1.getAxis(AxisType.X);
        xAxis.setMin(1);
        xAxis.setMax(12);
        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
        yAxis.setLabel("Montos (en millones de Guaranies)");
        yAxis.setMin(50);
        yAxis.setMax(1000);
       
         
        animatedModel2 = initBarModel();
        animatedModel2.setTitle("Prespuesto Anual");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(200);
    }
     
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        BarChartSeries boys = new BarChartSeries();
        boys.setLabel("Previsto");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);
 
        BarChartSeries girls = new BarChartSeries();
        girls.setLabel("Obligado");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 135);
        girls.set("2008", 120);
 
        BarChartSeries ejectutado = new BarChartSeries();
        ejectutado.setLabel("Ejecutado");
        ejectutado.set("2004", 40);
        ejectutado.set("2005", 70);
        ejectutado.set("2006", 150);
        ejectutado.set("2007", 100);
        ejectutado.set("2008", 120);
        
        model.addSeries(boys);
        model.addSeries(girls);
        model.addSeries(ejectutado);
        return model;
    }
     
    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries series0 = new LineChartSeries();
        series0.setLabel("Previsto");
 
        series0.set(1, 4);
        series0.set(2, 2);
        series0.set(3, 3);
        series0.set(4, 5);
        series0.set(5, 9);
        series0.set(6, 4);
        series0.set(7, 2);
        series0.set(8, 3);
        series0.set(9, 5);
        series0.set(10, 9);
        series0.set(11, 9);
        series0.set(12, 9);
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Obligado");
 
        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);
        series1.set(6, 4);
        series1.set(7, 2);
        series1.set(8, 3);
        series1.set(9, 5);
        series1.set(10, 9);
        series1.set(11, 9);
        series1.set(12, 9);
        
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Ejecutado");
 
        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);
 
        model.addSeries(series1);
        model.addSeries(series2);
        model.addSeries(series0);
         
        return model;
    }
    
   
     
    
}