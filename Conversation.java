import java.util.*; // Import the Scanner class
import java.lang.*;

/** 
 * Run a user selected amount of conversation. Then it will take turns printing messages to the user and accepting responses until the specified number of rounds are complete. Finally it will print a transcript of the entire conversation. If there is a first/second person pronoun in the user respond, the program will swap it to second/first pronoun respectively.
 * 
 * @author Sabrina Knight, Anh Nguyen
 * @version January 2022
 */

class Conversation {
	/** Use to output a response from computer */
	public static String[] cannedPhrases = {"What?", "I do not understand that", "How about no?"};
	
  public static void main(String[] arguments) {

    //Declare variables.
    Integer round;
    Scanner in;
    String respond;
    Random random = new Random(); 

    //Dummy array to record dialogue
    String[] dialogue=new String[] {"Ok! You start!"};
		int dialogueLen = dialogue.length;

    //List of mirrior-able phrases and what to substitution with.
    String[] match = new String[] {"I", "ME", "AM", "YOU", "MY", "YOUR"};
    String[] substitution = new String[] {"YOU", "YOU", "ARE", "I", "YOUR", "MY"};

    //Ask for number of rounds of conversation.
    in = new Scanner(System.in);
    System.out.println("How many rounds of conversation?");
    round = in.nextInt();

    //Start conversation
    System.out.println("Computer: Ok! You start!");
    
    for (int i = 1; i <= round; i++) 
			{
      //User respond
      in = new Scanner(System.in);
      System.out.print("You: ");
      respond = in.nextLine(); 

      //Record the response
      List<String>l = new ArrayList<String>(Arrays.asList(dialogue));
      l.add(respond);
      dialogue = l.toArray(dialogue);

			//boolean var to check if we need to mirror word or not
			boolean needSub = false;

			//capitalise the user respond
			respond = respond.toUpperCase();

			//list to keep track of what have already been mirriored.
			List<Integer> alreadyMirror = new ArrayList<Integer>();
			//split respond into an array
			String original[] = respond.split(" ");
			String[] respWords = respond.split(" ",0);
			List<Integer> subbedIndex = new ArrayList<Integer>();

      //Determine if the sentence can be mirror and mirror the sentence
      for (int r=0; r < match.length; r++) 
				{
				subbedIndex.clear();
				int n = 0;
				while (n < original.length)
				{
					if (original[n].equals(match[r])){
						subbedIndex.add(n);
						needSub = true;
					}
					n++;
				}

				for (int x: subbedIndex)
				{
					respWords[x] = substitution[r];
				}
			}

			// Print out mirrored sentence.
			if (needSub == true)
				{	
					respond = String.join(" ", respWords);
					System.out.println("Computer: " + respond);
					l = new ArrayList<String>(Arrays.asList(dialogue));
					l.add(respond);
					dialogue = l.toArray(dialogue);

				}
      //If no substitution is needed, randomly choose and print a canned respond.
      else 
				{
        int number = random.nextInt(dialogueLen);
        respond = cannedPhrases[number];
        System.out.println("Computer: " + respond);

				//save canned response to dialogue array
        l = new ArrayList<String>(Arrays.asList(dialogue));
        l.add(respond);
        dialogue = l.toArray(dialogue);
      	}
			}
			
			//End conversation
			System.out.println("Computer: Bye!");

			//Print out transcript
			System.out.println("\nTRANSCRIPT: ");
			for(int n =0; n < dialogue.length; n++) 
			{
      	System.out.println(dialogue[n]);
    	}
    
  }
}