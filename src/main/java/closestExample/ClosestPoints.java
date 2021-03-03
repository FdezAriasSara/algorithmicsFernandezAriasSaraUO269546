package closestExample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class ClosestPoints {
	private List<Point> points; //class to work with points
	
	public ClosestPoints() {
		this.points = new ArrayList<Point>();
	}
	  
	public void loadPoints(String name) {
		String line = "";
		StringTokenizer pair; //to get the two parts of each line (x and y coordinate of one point)
		BufferedReader reader = null; //to read each line of the file
		try {
			reader = new BufferedReader(new FileReader(name));
			int n = Integer.parseInt(reader.readLine()); //in the first line of the file we have the number of points 
			for (int i=0; i<n; i++) {
				line = reader.readLine();
				pair = new StringTokenizer(line);
				Point point = new Point(Integer.parseInt(pair.nextToken()), Integer.parseInt(pair.nextToken()));
				points.add(point); //points between 0..999999
			}
			reader.close();
		} catch(Exception e) { 
			 System.out.println(e.getMessage()); 
		}
	}
	
	public void loadRandomPoints(int n) {
		Random r = new Random();
		for (int i=0; i<n; i++) {
			int x = r.nextInt(1_000_000);
			int y = r.nextInt(1_000_000);
			points.add(new Point(x, y)); 
		}
	}
	  
	public void printPoints() {
		System.out.println("\n*******************************");
		System.out.println("SIZE OF THE CLOUD OF POINTS = " + points.size());
		System.out.println("POINTS ARE:");
		for (Point point : points)
			System.out.println(point);
		System.out.println();
	}
	
	//SOLUTION USING BRUTE FORCE - O(n^2)
	public Pair bruteForce() {
		return this.bruteForce(this.points);
	}
	
	private Pair bruteForce(List<Point> points) {
		Pair closestPoints = new Pair(points.get(0), points.get(1)); //the 2 first points by default
		closestPoints.setDistance(calculateDistance(points.get(0), points.get(1)));
		
		for (int i=0; i<points.size(); i++) {
			Point p1 = points.get(i);
			for (int j=0; j<points.size(); j++) {
				if (i != j) { //if it is not the same point
					Point p2 = points.get(j);
					double distance = calculateDistance(p1, p2);
					if (distance < closestPoints.getDistance())
						closestPoints.update(p1, p2, distance);
				}
			}
		}
		
		return closestPoints;
	}

	//CALCULATION OF THE DISTANCE BETWEEN 2 POINTS
	private double calculateDistance(Point p1, Point p2) {
		double a=p2.getX()-p1.getX();
		double b=p2.getY()-p1.getY();
		return  Math.hypot(a,b);//equivalent to Math.sqrt(a*a+b*b);
	}
	
	//SOLUTION USING DIVIDE AND CONQUER
	public Pair divideAndConquer() {
		//we sort the elements in ascending order by x
		Collections.sort(points, new PointComparator());//O(nlogn)
		return divide(0, points.size());
	}
	//D=2 B=2 K=1 -> O(nlogn)
	private Pair divide(int leftLimit, int rightLimit) {
		if(rightLimit-leftLimit>3) {//if big enough
			int center=(leftLimit+rightLimit)/2;
			Pair pairLeft= divide(leftLimit,center);//N/2
			Pair pairRight= divide(center,rightLimit);//N/2
			Pair pairCenter =merge(pairLeft, pairRight, leftLimit , rightLimit);//O(N)
			return smallestDistance(pairLeft, pairRight,pairCenter);//O(1)
			
		}//if trivial case
		//the complexity of using bruteforze wont be O(n^2). since the num of elements 	
		//doesnt depend on n. It will act as a constant.O(1) (trivial case.)
		return bruteForce(points.subList(leftLimit, rightLimit));
	}

	private Pair merge(Pair pairLeft, Pair pairRight, int leftLimit, int rightLimit) {
		int center= (leftLimit+rightLimit)/2;
		double min=(pairLeft.getDistance()<pairRight.getDistance())? pairLeft.getDistance() : pairRight.getDistance();//IN THE SLIDES IS D
		Point centralPoint= points.get(center);//border between the two parts.
		Point pairCenter=pairLeft;//until we have more info(it can be right).
		List<Point> centralPoints=new ArrayList<>();
		
		
		//we take points from the central position to the right for a maximum distance of MIN
		//(if not ,it's impossible to improve the current MIN value)
		for(Point p: reverseLeft) {
			if(p.getX()<centralPoint.getX()+min)
				centralPoints.add(p);
			else break ;
		}

		//we take points from the central position to the left for a maximum distance of MIN
		//(if not ,it's impossible to improve the current MIN value)
		for(Point p: points.subList(center,rightLimit)) {
			if(p.getX()<centralPoint.getX()+min)
				centralPoints.add(p);
			else break ;
		}
		return null;
	}

	private Pair smallestDistance(Pair pairLeft, Pair pairRight, Pair pairCenter) {
		if(pairLeft.getDistance()< pairRight.getDistance()) {
			if(pairLeft.getDistance() < pairCenter.getDistance()) 
				return pairLeft;
				else return pairCenter;
				
			
			
		}else if (pairRight.getDistance() < pairCenter.getDistance()) {
			return pairRight;
		}else return pairCenter;
		//THIS IMPLEMENTATION DOESNT DEPEND ON SIZES, AND ALWAYS HAS THE SAME NUMBER OF LINES. 
		//IT COMPLEXITY IS O(1)
	}
}
