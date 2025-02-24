import java.util.*;

class CardCollection {
    private Map<String, List<String>> cardMap;
    private Scanner scanner;

    public CardCollection() {
        cardMap = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public void addCard(String symbol, String cardName) {
        cardMap.computeIfAbsent(symbol, k -> new ArrayList<>()).add(cardName);
    }

    public void findCardsBySymbol(String symbol) {
        List<String> cards = cardMap.get(symbol);
        if (cards != null && !cards.isEmpty()) {
            System.out.println("Cards under " + symbol + ": " + cards);
        } else {
            System.out.println("No cards found for symbol: " + symbol);
        }
    }

    public void displayAllCards() {
        if (cardMap.isEmpty()) {
            System.out.println("No cards in the collection.");
            return;
        }
        System.out.println("\nCard Collection:");
        for (Map.Entry<String, List<String>> entry : cardMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void start() {
        while (true) {
            System.out.println("\nCard Collection System");
            System.out.println("1. Add Card");
            System.out.println("2. Find Cards by Symbol");
            System.out.println("3. Display All Cards");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter card symbol (e.g., Hearts, Spades): ");
                    String symbol = scanner.nextLine();
                    System.out.print("Enter card name (e.g., Ace, King): ");
                    String cardName = scanner.nextLine();
                    addCard(symbol, cardName);
                    System.out.println("Card added successfully!");
                    break;
                case 2:
                    System.out.print("Enter card symbol to search: ");
                    String searchSymbol = scanner.nextLine();
                    findCardsBySymbol(searchSymbol);
                    break;
                case 3:
                    displayAllCards();
                    break;
                case 4:
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        CardCollection cardCollection = new CardCollection();
        cardCollection.start();
    }
}
