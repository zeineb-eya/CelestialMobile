/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.entities.Reservation;
import com.mycompany.myapp.services.ServiceReclamation;
import com.mycompany.myapp.services.ServiceReservation;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class StatistiqueReclamForm extends Form {
      Form current;
    ArrayList<Reclamation> reclamationArrayList = new ArrayList<>();
     ServiceReclamation reclamationService  = new ServiceReclamation();
    int envoyeNumber = 0;
    int traiteNumber = 0;
   

    public StatistiqueReclamForm(Form previous) {
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("Statistiques des réclamations");
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        reclamationArrayList = reclamationService.displayReclamations();
     //   reclamationArrayList = ServiceReclamation.getAllReservations();
        for (Reclamation reclamation : reclamationArrayList) {
            if (reclamation.getEtatReclamation().equals("envoye"))                 
                envoyeNumber++;
            else if (reclamation.getEtatReclamation().equals("traite"))                 
                traiteNumber++;
             
                   }
        double[] values = new double[]{envoyeNumber, traiteNumber};
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(128);
        renderer.setChartTitle("Reclamations");
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer simpleSeriesRendererSpectacle = renderer.getSeriesRendererAt(0);
        simpleSeriesRendererSpectacle.setGradientEnabled(true);
        simpleSeriesRendererSpectacle.setGradientStart(0, ColorUtil.YELLOW);
        simpleSeriesRendererSpectacle.setGradientStop(0, ColorUtil.YELLOW);
        simpleSeriesRendererSpectacle.setHighlighted(true);

        SimpleSeriesRenderer simpleSeriesRendererFilm = renderer.getSeriesRendererAt(1);
        simpleSeriesRendererFilm.setGradientEnabled(true);
        simpleSeriesRendererFilm.setGradientStart(0, ColorUtil.GREEN);
        simpleSeriesRendererFilm.setGradientStop(0, ColorUtil.GREEN);
        simpleSeriesRendererFilm.setHighlighted(true);
        
       
        // Create the chart ... pass the values and renderer to the chart object.
        PieChart pieChart = new PieChart(buildCategoryDataset("Reclamation", values), renderer);
        // Wrap the chart in a Component so we can add it to a form
        ChartComponent chartComponent = new ChartComponent(pieChart);
        add(chartComponent);
getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(32);
        renderer.setLegendTextSize(64);
        renderer.setLabelsColor(ColorUtil.BLACK);
        renderer.setAxesColor(ColorUtil.BLACK);

        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    /**
     * Builds a category series using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
    protected CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);
        series.add("envoye", values[0]);
        series.add("traite", values[1]);
       
        return series;
    }       
   

}
