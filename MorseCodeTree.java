import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.Queue;
import java.util.LinkedList;

public class MorseCodeTree extends BinaryTree {

    /**
     * MorseCodeTree Constructor that builds itself from a file
     */
    public MorseCodeTree() {
        /** Sets root to dummy value */
        this.root = new Node<Character>('_');
        /** Queue to hold letters */
        Queue<Character> characterQ = new LinkedList<Character>();
        /** Queue to hold queues of morse code characters */
        Queue<Queue<Character>> codeQ = new LinkedList<Queue<Character>>(); //

        /** File to read morse code tree from */
        File file = new File("src/MorseCodeTree.txt");
        try {
            Scanner scan = new Scanner(file);

            /** Reads data from file into queues */
            while (scan.hasNext()) {
                String line = scan.nextLine();
                Character letter = line.charAt(0);
                String codeString = line.substring(2);
                char[] charCode = codeString.toCharArray();
                Queue<Character> tempQ = new LinkedList<Character>();

                for(char ch: charCode) {
                    tempQ.offer(ch);
                }

                characterQ.offer(letter);
                codeQ.offer(tempQ);
            }

            scan.close();

            /** Uses queues to build the tree */
            while(!characterQ.isEmpty()) {
                buildMorseCodeTree(root, characterQ.poll(), codeQ.poll());
            }
        }
        catch(FileNotFoundException fnf) {
            System.out.println("File not found.");
        }

    }

    /**
     * Uses recursion to build the morse code tree
     *
     * @param localRoot the root we are currently at
     * @param letter data that will be read into a new node
     * @param code morse code equivalent of letter that tells us where to insert the letter
     * @return
     */
    private Node<Character> buildMorseCodeTree(Node<Character> localRoot, Character letter, Queue<Character> code) {
        if (code.isEmpty()) {
            return new Node<Character>(letter);
        }
        else if (code.peek() == '*') {
            code.poll();
            localRoot.left = buildMorseCodeTree(localRoot.left, letter, code);
        }
        else if (code.peek() == '-') {
            code.poll();
            localRoot.right = buildMorseCodeTree(localRoot.right, letter, code);
        }

        return localRoot;
    }

    /**
     * Starter method for decode
     * @param message morse code to be translated
     * @return the letter translation of the given morse code
     */
    public String decode(String message) {
        String[] stringArr = message.split(" ");
        StringBuilder sb = new StringBuilder();
        for(String s: stringArr) {
            sb.append(decode(root, s));
        }
        return sb.toString();
    }

    /**
     * Recursive decode method
     * @param localRoot the current root
     * @param message the message to be translated
     * @return
     */
    private String decode(Node<Character> localRoot, String message) {

        if (message.isEmpty()) {
           return localRoot.data.toString();
        }
        else if (message.startsWith("*")) {
            String newMessage = message.replaceFirst("\\*", "");
            return decode(localRoot.left, newMessage);
        }
        else if (message.startsWith("-")) {
            String newMessage = message.replaceFirst("-", "");
            return decode(localRoot.right, newMessage);
        }
        else {
            return "Invalid Input";
        }
    }
}
