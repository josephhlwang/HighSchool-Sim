public class GameRunner
{
	public static void main (String[] args)
	{
      // Where the magic happens and the game is started to play
      // If you are seeing this, that means you've successfully opened the correct Java files to play
      // First, you should compile all 22 of the different Java files and ensure that there are no Compile Message errors
         // If there are errors, usually they include which files should be compiled before that specific one can be compiled first
      // Also ensure that the textfiles of EVENTS.txt & ENDINGS.txt are not Compressed
         // This can be fixed by first copying the files into another folder
         // Then deleting the original ones in this very folder (ICS-PROJECT-master)
         // Move the copiedtext files over again back into this very folder (ICS-PROJECT-master)
      // If the game can run without any crashing, then you are welcome to close all of the Java windows besides GameRunner.java
      // and move up the screen so you can fully see what the game has to say!
      // You can decide to end the current simulation by pressing 'End' and the game will save (assuming you've reached the checkpoint)
      // With that, you can decide to return to your previous game or start a new game!
      // Enjoy the AYJ Simulator!
		Display d = new Display();
		d.startGame();
	}
}