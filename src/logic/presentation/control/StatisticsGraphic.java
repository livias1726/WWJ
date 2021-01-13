package logic.presentation.control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.application.control.StatisticsControl;
import logic.exceptions.DatabaseFailureException;
import logic.presentation.GraphicHandler;
import logic.presentation.Sections;
import logic.presentation.bean.BusinessInCountryBean;

public class StatisticsGraphic implements Initializable {
	
	@FXML
    private AnchorPane pane;
	
	@FXML
	private Label busName;
	 
	@FXML
    private LineChart<String, Number> popularityGraph;

    @FXML
    private StackedBarChart<String, Number> averageGraph;

    @FXML
    private LineChart<String, Number> compGraph;

    @FXML
    private TextField budget;

    @FXML
    private ChoiceBox<String> currBox;

    @FXML
    private Button feasBtn;

	private BusinessInCountryBean business;

	public StatisticsGraphic(BusinessInCountryBean business) {
		this.business = business;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		busName.setText(business.getName());
		feasBtn.disableProperty().bind(budget.textProperty().isEmpty());

		ObservableList<String> currency = FXCollections.observableArrayList("$", "£", "€");
		currBox.setItems(currency);
		currBox.setValue(currency.get(0));

		try {
			StatisticsControl.getInstance().retrieveBusinessStatistics(business);
		} catch (DatabaseFailureException e) {
			GraphicHandler.popUpMsg(AlertType.ERROR, e.getMessage());
			goBack();
		}

		initPopularity();
		initAverage();
		initCompetitors();
	}

	private void initCompetitors() {
		XYChart.Series<String, Number> series = new XYChart.Series<>();

        series.getData().add(new XYChart.Data<>("2016", business.getCompetitors().get(0)));
        series.getData().add(new XYChart.Data<>("2017", business.getCompetitors().get(1)));
        series.getData().add(new XYChart.Data<>("2018", business.getCompetitors().get(2)));
        series.getData().add(new XYChart.Data<>("2019", business.getCompetitors().get(3)));
        series.getData().add(new XYChart.Data<>("2020", business.getCompetitors().get(4)));
        
        compGraph.getData().add(series);
        compGraph.setLegendVisible(false);
	}

	private void initAverage() {
		averageGraph.getYAxis().setLabel(business.getCountry().getCurrency());
		
		XYChart.Series<String, Number> series = new XYChart.Series<>();   
		series.getData().add(new XYChart.Data<>("Earnings", business.getAverageEarnings()));
        series.getData().add(new XYChart.Data<>("Costs", business.getAverageCost()));
      
        averageGraph.getData().add(series);
        averageGraph.setLegendVisible(false);
	}

	private void initPopularity() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        series.getData().add(new XYChart.Data<>("2016", business.getPopularity().get(0)));
        series.getData().add(new XYChart.Data<>("2017", business.getPopularity().get(1)));
        series.getData().add(new XYChart.Data<>("2018", business.getPopularity().get(2)));
        series.getData().add(new XYChart.Data<>("2019", business.getPopularity().get(3)));
        series.getData().add(new XYChart.Data<>("2020", business.getPopularity().get(4)));

        popularityGraph.getData().add(series);
        popularityGraph.setLegendVisible(false);
	}

	@FXML
    public void calculateFeasibility() {
		Stage popup = GraphicHandler.openSection(pane, Sections.FEASIBILITY, new FeasibilityGraphic(business, budget.getText()));
		popup.centerOnScreen();
		popup.show();
    }

    @FXML
    public void goBack() {
    	Stage st = (Stage)pane.getScene().getWindow();
    	st.close();
    }
}
