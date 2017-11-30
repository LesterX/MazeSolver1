/**
 * ??
 *
 * @author Yimin Xu 250876566 CS1027
 * @author 
 * @version
 */
 
import java.io.*;

public class MazeSolverToo 
{
	
	/**
	 * @param args
	 */
	public static void main (String[] args) 
	{
		try
		{
			//Open the file the reference the start of the maze
			Maze aMaze = new Maze("maze2.txt");
			Hexagon start = aMaze.getStart();
			start.setSteps(0);
			
			//Build priority queue of hexagon to store the hexagons
			LinkedPriorityQueue<Hexagon> track = new LinkedPriorityQueue<Hexagon>();
			LinkedPriorityQueue<Hexagon> secondTrack = new LinkedPriorityQueue<Hexagon>();
			track.enqueue(start);
			//Set the counters
			int totalSteps = 0;
			double shortest = 0;
			
			//The boolean variable will become true if the end is found
			boolean found = false;
			
			while (!track.isEmpty())
			{	
				//Dequeue the current hexagon from the priority queue
				Hexagon currentHexagon = track.dequeue();
				totalSteps ++;
				
				//Change the color of the current hexagon based on its type
				if (currentHexagon.isStart())
					currentHexagon.setStarted();
				else if (currentHexagon.isEnd())
				{
					currentHexagon.setFinished();
					found = true;
					aMaze.repaint();
					shortest = currentHexagon.getSteps();
					break;
				}else
					currentHexagon.setDequeued();
				
				
				
				//Check the neighbors and enqueue them
				for (int i = 0; i <= 5; i ++)
				{
					//Find the next hexagon which is unvisited and not a wall
					Hexagon newHexagon = currentHexagon.getNeighbour(i);
					if (newHexagon != null)
					{
						if(!newHexagon.isDequeued() && !newHexagon.isEnqueued() && !newHexagon.isWall())
						{
							//f(x) = g(x) + h(x)
							//g(x) is "steps to me", h(x) is distance to the end.
							newHexagon.setSteps(currentHexagon.getSteps() + 1);
							double gValue = newHexagon.getSteps();
							double hValue = newHexagon.distanceToEnd(aMaze);
							double fValue = gValue + hValue;
							
							//Add the hexagon into the priority queue
							track.enqueue(newHexagon, fValue);
							newHexagon.setEnqueued();
						}
					}
				}
				aMaze.repaint();
			}
			
			
			if (found)
				System.out.println("The end of maze was found.");
			else
				System.out.println("The end of maze was not found.");
			System.out.println((int)shortest + " steps to the end.");
			System.out.println("There are still " + track.size() + " hexagons in the queue.");
			System.out.println(totalSteps + " steps were taken.");
			
		}
		
		catch (IOException e)
		{
			System.out.println("Can't find the maze file.");
		} 
		
		catch (UnknownMazeCharacterException e)
		{
			System.out.println("Unknown maze character found.");
		}
		
		catch (EmptyCollectionException e)
		{
			System.out.println("Empty collection.");
		}
		
		catch (NullPointerException e)
		{
			System.out.println("Null pointer exception.");
		}
		
		catch (InvalidNeighbourIndexException e)
		{
			System.out.println("Invalid neighbour index.");
		}
		
		catch (RuntimeException e)
		{
			System.out.println("There is something wrong.");
		}
	}
}
