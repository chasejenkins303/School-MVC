package edu.unca.csci338.domain.data;

import edu.unca.csci338.domain.model.Building;
import edu.unca.csci338.domain.model.IBuildingDataChangedEvent;
import edu.unca.csci338.domain.model.IStudentDataChangedEvent;
import edu.unca.csci338.domain.model.Professor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class BuildingData extends Data{
	
	/**

	 * Method for establishing a connection to the database

	 * @param dbToConnectTo (String)

	 */
	private static List<IBuildingDataChangedEvent> buildingChangedEvents=new ArrayList<IBuildingDataChangedEvent>();
	
	private Connection conn = null;

	
	/**

	 * gets the information stored in the database associated with a certain building id

	 * and stores it in a Building object

	 * @param ID (int)

	 * @return building (Building)

	 */

		public Building getBuilding(int ID) {

		//getBuilding() Variables

			PreparedStatement preparedStatement = null;

			ResultSet resultSet = null;

			Building  building = null;

			

		//sending out the request to the database and receiving an answer
//
//			try {
//
//				preparedStatement = conn.prepareStatement("Select * from buildings Where id=" + String.valueOf(ID));
//
//				resultSet = preparedStatement.executeQuery();
//
//			}	catch(SQLException e1){
//
//				e1.printStackTrace();
//
//			}
			resultSet=getById("buildings", ID);

			

		//in case of multiple pings

			try {

			//getting the last ping

				while (resultSet.next()) {

				//setting the variables needed to create a Building object

				    int id = resultSet.getInt("id");

				    int numRooms = resultSet.getInt("num_Rooms");

				    String name = resultSet.getString("name");

				//creating a building object

				    building = new Building(id,  name,  numRooms);

				}//end while

			} catch (SQLException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}//end try/catch

			

	        return building;

		}//end getBuilding()

		
		public Building getMostRecent() {
			ResultSet resultSet = null;
			Building building = null;
			
//			try {
//				preparedStatement = conn.prepareStatement("Select * from buildings Order by id DESC Limit 1");
//				 resultSet = preparedStatement.executeQuery();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			
			resultSet=getRecent("buildings");

	            try {
					while (resultSet.next()) {
						  int id = resultSet.getInt("id");

						    int numRooms = resultSet.getInt("num_Rooms");

						    String name = resultSet.getString("name");

						//creating a building object

						    building = new Building(id,  name,  numRooms);

					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            //System.out.println(student.getID());
	            return building;
		}
		

	/**

	 * gets all of the buildings stored in the Database and puts them into an array

	 * @return results (ArrayList<Buildings>)

	 */

		public ArrayList<Building> getBuildings() {

		//getBuildings() variables

			PreparedStatement preparedStatement = null;

			ResultSet resultSet = null;

			Building building = null;

			ArrayList<Building> results = new ArrayList<>();
			
			resultSet=getAll("buildings");

			

			try {

				while(resultSet.next()) {//cycles through all of the results

				//sets variables needed to create Building objects

					int id = resultSet.getInt("id");

					int numRooms = resultSet.getInt("num_Rooms");

					String name = resultSet.getString("name");

				//create Building object using info

					building = new Building(id, name, numRooms);

				//add building to array of Buildings

					results.add(building);

				}//end while

				

				return results;

				

			}	catch(SQLException e) {

				e.printStackTrace();

			}//end try/catch

			

			return null;

		}//end getBuildings()

		

	/**

	 * stores the information in a Building object into the buildings table in the database

	 * @param building (Building)

	 */

		public void addBuilding(Building building) {

		//addBuilding() variables

			PreparedStatement preparedStatement = null;

			

		//getting the data prepared to make the request, sending the request, and executing the request

			try {

			//getting the data prepared to make the request

				int numRooms = building.getNumRooms();

				String name = building.getName();

				

			//sending the request

				preparedStatement = conn.prepareStatement("insert into buildings (num_Rooms, name) values(?,?)");
				preparedStatement.setInt(1, numRooms);
				preparedStatement.setString(2, name);
				

			//executing the request

				preparedStatement.executeUpdate();

			}	catch(SQLException e) {

				e.printStackTrace();

			}//end try/catch

		}//end addBuilding()

		

	/**

	 * changing/updating the data in the database for a building so that it matches the data contained

	 * in the parameter object

	 * @param building (Building)

	 */

		public void updateBuilding(Building building) {

		//updateBuilding() variables

			PreparedStatement preparedStatement = null;

			int ID = building.getId();

			int numRooms = building.getNumRooms();

			String name = building.getName();

			

		//sending out a request for the database and executing the request

			try {

				preparedStatement = conn.prepareStatement("UPDATE buildings SET name = ?, num_rooms = ? WHERE id = ?");
				
				preparedStatement.setString(1, name);
				preparedStatement.setInt(2, numRooms);
				preparedStatement.setInt(3, ID);
				preparedStatement.executeUpdate();
				
				for(IBuildingDataChangedEvent listener : buildingChangedEvents) {
					listener.onBuildingDataChanged(building);
				}

			} catch(SQLException e) {

				e.printStackTrace();

			}//end try/catch

		}//end updateBuilding()

		

	/**

	 * deletes the id and all other data associated with that id from the building database table

	 * @param ID (int)

	 */

		public void deleteBuilding(int ID) {

		delete(ID, "buildings");

		}//end deleteBuilding()
		
		
		public static void AddOnStudentDataChangeEventListner(IBuildingDataChangedEvent listener) {
			buildingChangedEvents.add(listener);
		}

}

