package edu.unca.csci338.domain.model;

public interface IBuildingDataChangedEvent {
	
	public void onBuildingDataChanged(Building building);

}
