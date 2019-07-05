package Store;
import dataStructures.DictionaryTree;
import dataStructures.Graph;
import dataStructures.LinkedList;
import dataStructures.Vector;

public class DepartmentDirectory {
	
	private Graph floorPlan;
	private DictionaryTree departmentTree;
	

	public DepartmentDirectory() {
		floorPlan = new Graph();
		departmentTree = new DictionaryTree();
	}
	
//	public LinkedList getList() {
//		return departmentList;
//	}
	
	// Takes in string and generates unique string.
	// This is a crude mimic of hash key generation
	private long generateId(String string) {
		long key = 7;
		for (int i = 0; i < string.length(); i++)
			key = key*7 + string.charAt(i);
//		System.out.println(key);
		return key;
	}
	
	public long getId(String department) {
		return generateId(department);
	}
	
	public Object lookup(long departmentId) {
		return departmentTree.findKey(departmentId);
	}
	
	
	public Object lookup(String departmentName) {
		long departmentId = generateId(departmentName);
		return departmentTree.findKey(departmentId);
	}
	
	public void addDepartment (String departmentName) {	
		if(lookup(departmentName) != null) {
			System.out.println("Error: Department already exists.");
			return;
		}
		
//		Department newDepartment = new Department(departmentId, departmentName);
//		departmentGraph.addNode(newDepartment);
//		floorPlan.addNode(departmentId, departmentName);
		long departmentId = generateId(departmentName);
		departmentTree.add(departmentId, departmentName);
		floorPlan.addNodeSorted(departmentId);
	}

	public void connectDepartments(String department1, String department2, int weight) {
		long departmentId1 = generateId(department1);
		long departmentId2 = generateId(department2);

		if(lookup(department1) == null || lookup(department2) == null) {
			System.out.println("Error: At least one of the departments does not exist");
			return;
		}
		floorPlan.addEdge(departmentId1, departmentId2, weight);
		floorPlan.addEdge(departmentId2, departmentId1, weight);
	}
	
	public void shortestPath(String department1, String department2) {
		long departmentId1 = generateId(department1);
		long departmentId2 = generateId(department2);

		if(lookup(department1) == null || lookup(department2) == null) {
			System.out.println("Error: At least one of the departments does not exist");
			return;
		}
		LinkedList path = floorPlan.findPath(departmentId1, departmentId2);
		String s = "";
		for(int i = 1; i<= path.getSize(); i++) {
			long temp = (long) path.getElementData(i);
			s = s+ "\n"+ departmentTree.findKey(temp);
		}
		System.out.println(s);
	}
	
	public Vector optimalPath(Vector departmentNameList) {
		Vector departmentIdList = new Vector();
		for(int i =0; i<departmentNameList.size(); i++) {
			long departmentId = generateId(departmentNameList.get(i).toString());
			departmentIdList.addLast(departmentId);
		}
		
		departmentIdList = floorPlan.findNNodePath(departmentIdList);
		departmentNameList = new Vector();
		for(int i =0; i<departmentIdList.size(); i++) {
			String departmentName = (String) lookup((long)departmentIdList.get(i));
			departmentNameList.addLast(departmentName);
		}

		return departmentNameList;
	}
}
