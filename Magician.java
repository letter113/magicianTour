package magician;

import java.util.LinkedList;

/*
* http://community.topcoder.com/stat?c=problem_statement&pm=2346&rd=4775
*/

class MagicianTour{
	private String[] roads;
	private int[] populations;
	public MagicianTour(){}
	public int bestDifference(String[] roads, int[] populations){
		this.roads = roads;
		this.populations = populations;
		int maxCity = -1 * populations.length;
		int maxPopulation = 0;
		int totalShow1 = 0;
		int totalShow2 = 0;
		LinkedList <Integer> cities = new LinkedList<Integer>();
		for (int i=0;i< populations.length;i++){
			cities.add(i);
		}
		while(cities.size() != 0){
			for(int i=0; i< cities.size(); i++){
				int currentCity = cities.get(i);
				if (maxCity == -1 * populations.length || populations[currentCity] > maxPopulation){
					maxCity = currentCity;
					maxPopulation = populations[currentCity];
				}
				int neighborPopulation = this.getNeighborsPopulation(currentCity);
				if ( neighborPopulation > maxPopulation){
					maxCity = currentCity * -1;	
					maxPopulation = neighborPopulation;
				}
			}
			if (totalShow1 > totalShow2){
				totalShow2 += maxPopulation;
			}else{
				totalShow1 += maxPopulation;
			}
			if(maxCity > 0){
				cities.removeFirstOccurrence(maxCity);
			}else{
				for (int neighbour: this.getNeighbors(-1 * maxCity)){
					cities.removeFirstOccurrence(neighbour);
				}
			}
		}
		if (totalShow1 > totalShow2){
			return totalShow1 - totalShow2;
		}else{
			return totalShow2 - totalShow1;
		}
	}	
		
	private LinkedList<Integer> getNeighbors(int city){
		LinkedList<Integer> neighbours = new LinkedList<Integer>();
		for (int i =0; i< this.roads[city].length(); i++){
			if (this.roads[city].substring(i, i+1).equals("1")){
				neighbours.add(i);
			}
		}
		return neighbours;
	}

	private int getNeighborsPopulation(int city){
		int population = 0;
		for (int i =0; i< this.roads[city].length(); i++){
			if (this.roads[city].substring(i, i+1).equals("1")){
				population += this.populations[i];
			}
		}
		return population;
	}
	
	public static void main(String []args){
		String []roads = new String []{"0000",
				 "0010",
				 "0101",
				 "0010"};

		int []populations = new int []{8,10,15,10};
		System.out.println(new MagicianTour().bestDifference(roads, populations));
	}
}
