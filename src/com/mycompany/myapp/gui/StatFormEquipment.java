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
import com.mycompany.myapp.entities.Equipement;
import com.mycompany.myapp.services.ServiceEquipment;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class StatFormEquipment extends Form {
    Form current;
    ArrayList<Equipement> EquipementArrayList = new ArrayList<>();
    ServiceEquipment EquipementService = new ServiceEquipment();
    int confirmedNumber = 0;
    int cancelleddNumber = 0;

    public StatFormEquipment(Form previous) {
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("Stats Planning");
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        EquipementArrayList = EquipementService.getAllEquipments();
        for (Equipement E : EquipementArrayList) {
            
            if (E.getEtat_equipement().equals("new"))                 
                confirmedNumber++;
             else if (E.getEtat_equipement().equals("used"))                 
                cancelleddNumber++;
                   }
        double[] values = new double[]{ confirmedNumber,cancelleddNumber};
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(128);
        renderer.setChartTitle("Equipement");
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
        
        SimpleSeriesRenderer simpleSeriesRendererCancelled = renderer.getSeriesRendererAt(2);
        simpleSeriesRendererCancelled.setGradientEnabled(true);
        simpleSeriesRendererCancelled.setGradientStart(0, ColorUtil.MAGENTA);
        simpleSeriesRendererCancelled.setGradientStop(0, ColorUtil.MAGENTA);
        simpleSeriesRendererCancelled.setHighlighted(true);
        // Create the chart ... pass the values and renderer to the chart object.
        PieChart pieChart = new PieChart(buildCategoryDataset("Equipement", values), renderer);
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
        series.add("new", values[0]);
        series.add("used", values[1]);
        return series;
    }
}
