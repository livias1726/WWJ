package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.ChoiceBox;
import logic.presentation.bean.BusinessInCountryBean;
import logic.presentation.control.FavouriteBusinessGraphic;

public class TestFavouriteBusinessGraphic {

	JFXPanel fxPanel = new JFXPanel();
	
	@Test
	public void testOrderResultInsertion() {
		List<BusinessInCountryBean> businesses = new ArrayList<>();
		
		int size = 4;
		for(int i=0; i<size; i++) {
			BusinessInCountryBean bean = new BusinessInCountryBean();
			bean.setAverageEarnings((float) i);  //0, 1, 2, 3
			bean.setAverageCost((float) (size-i)); //4, 3, 2, 1
			
			businesses.add(bean);
		}
		
		assertTrue(businesses.get(0).getAverageEarnings() == 0 && businesses.get(size-1).getAverageEarnings() == 3);
	}
	
	@Test
	public void testOrderResultEarnings() {
		ChoiceBox<String> filterBox = new ChoiceBox<>();
		ObservableList<String> filters = FXCollections.observableArrayList("Earnings", "Management cost");
		filterBox.setItems(filters);
		filterBox.setValue(filters.get(0));
		
		List<BusinessInCountryBean> items = new ArrayList<>();
		
		int size = 4;
		for(int i=0; i<size; i++) {
			BusinessInCountryBean j = new BusinessInCountryBean();
			j.setAverageEarnings((float) i); 
			j.setAverageCost((float) (size-i)); 
			
			items.add(j);
		}	
		
		FavouriteBusinessGraphic graph = new FavouriteBusinessGraphic();
		
		graph.orderResults(items, 0);
		
		assertTrue(items.get(0).getAverageEarnings() == 3 && items.get(size-1).getAverageEarnings() == 0);
	}
	
	@Test
	public void testOrderResultCost() {
		ChoiceBox<String> orderBox = new ChoiceBox<>();
		ObservableList<String> items = FXCollections.observableArrayList("Earnings", "Management cost");
		orderBox.setItems(items);
		orderBox.setValue(items.get(0));
		
		List<BusinessInCountryBean> list = new ArrayList<>();
		
		int size = 4;
		for(int i=0; i<size; i++) {
			BusinessInCountryBean b = new BusinessInCountryBean();
			b.setAverageEarnings((float) i); 
			b.setAverageCost((float) (size-i));
			
			list.add(b);
		}	
		
		FavouriteBusinessGraphic graph = new FavouriteBusinessGraphic();
		
		graph.orderResults(list, 1);
		
		assertTrue(list.get(0).getAverageCost() == 4 && list.get(size-1).getAverageCost() == 1);
	}

}
