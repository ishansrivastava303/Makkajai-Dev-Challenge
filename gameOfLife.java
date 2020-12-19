import java.io.*;
import java.util.*;
class Coordinates{
	int x,y;
	public Coordinates(int x,int y)
	{
		this.x=x;
		this.y=y;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + y;
		result = prime * result + x;
		return result;
	}


	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		if (y != other.y)
			return false;
		if (x != other.x)
			return false;
		return true;
	}
}
public class gameOfLife {
	ArrayList<Coordinates>nextGenerationCells=new ArrayList<Coordinates>();//This will store the coordinates of alive cells of next generation
	HashSet<Coordinates>liveCells=new HashSet<Coordinates>(); //This will store coordinates of  alive cells of the current generation
	HashSet<Coordinates>requiredCoordinates=new HashSet<Coordinates>();//This will store the alive cells and its neighbors coordinates of the current generation.
	public void currentUniverseInitialization() throws NumberFormatException, IOException
	{
		System.out.println("Enter the coordinates of alive cells");

		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in)); 
		String line;
		while ((line = stdin.readLine()) != null && line.length()!= 0)
		{
			String coordinates[]=line.split(",");

			int x=Integer.parseInt(coordinates[0]);
			int y=Integer.parseInt(coordinates[1]);
			liveCells.add(new Coordinates(x, y));

			requiredCoordinates.add(new Coordinates(x, y));
			requiredCoordinates.add(new Coordinates(x, y-1));
			requiredCoordinates.add(new Coordinates(x, y+1));
			requiredCoordinates.add(new Coordinates(x-1, y));
			requiredCoordinates.add(new Coordinates(x+1, y));
			requiredCoordinates.add(new Coordinates(x-1, y-1));
			requiredCoordinates.add(new Coordinates(x-1, y+1));
			requiredCoordinates.add(new Coordinates(x+1, y-1));
			requiredCoordinates.add(new Coordinates(x+1, y+1));

		}
	}
	public void nextUniverseInitialization()
	{
		for(Coordinates c:requiredCoordinates)
		{
			int x=c.x;
			int y=c.y;
			Coordinates currentCoordinate=new Coordinates(x, y);
			int count=0;
			if(liveCells.contains(currentCoordinate))
			{
				if(liveCells.contains(new Coordinates(x, y-1)))
					count++;

				if(liveCells.contains(new Coordinates(x, y+1)))
					count++;

				if(liveCells.contains(new Coordinates(x-1, y)))
					count++;

				if(liveCells.contains(new Coordinates(x+1, y)))
					count++;

				if(liveCells.contains(new Coordinates(x-1, y-1)))
					count++;

				if(liveCells.contains(new Coordinates(x-1, y+1)))
					count++;

				if(liveCells.contains(new Coordinates(x+1, y-1)))
					count++;

				if(liveCells.contains(new Coordinates(x+1, y+1)))
					count++;
				if(count==2 || count==3)
					nextGenerationCells.add(currentCoordinate);
			}
			else
			{
				if(liveCells.contains(new Coordinates(x, y-1)))
					count++;

				if(liveCells.contains(new Coordinates(x, y+1)))
					count++;

				if(liveCells.contains(new Coordinates(x-1, y)))
					count++;

				if(liveCells.contains(new Coordinates(x+1, y)))
					count++;

				if(liveCells.contains(new Coordinates(x-1, y-1)))
					count++;

				if(liveCells.contains(new Coordinates(x-1, y+1)))
					count++;

				if(liveCells.contains(new Coordinates(x+1, y-1)))
					count++;

				if(liveCells.contains(new Coordinates(x+1, y+1)))
					count++;
				if(count==3)
					nextGenerationCells.add(currentCoordinate);
			}

		}
	}
	public void universeInitialization() throws NumberFormatException, IOException
	{
		gameOfLife obj=new gameOfLife();
		currentUniverseInitialization();//This function will initialize the required data(i.e alive cells) from the current universe
		nextUniverseInitialization();//This function will use the required data(i.e alive cells) of current universe to find alive cells of next universe
		System.out.println("Next Generation alive cells:");
		for(Coordinates c:nextGenerationCells)
		{
			System.out.println(c.x+","+c.y);
		}
	}
	public static void main(String args[]) throws NumberFormatException, IOException
	{

		gameOfLife obj=new gameOfLife();
		obj.universeInitialization();
	}
}
