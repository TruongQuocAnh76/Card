package b3.view;

import b3.module.Card;
import b3.module.Random;
import java.awt.*;
import java.util.List;
import javax.swing.*;

public class View extends JFrame {
  private CardLayout cardLayout = new CardLayout();
  private JPanel mainPanel = new JPanel(cardLayout);
  private Random random = new Random(13);
  private JPanel cardsPanel = new JPanel(new GridLayout(0, 13, 0, 0));
  private JLabel cardNumberLabel = new JLabel(random.n + "", SwingConstants.CENTER);
  private Sound sound = new Sound();

  private Listener listener = new Listener(this);

  public View() {
    super("Card (not game)");
    init();
  }

  private void init() {
    this.setLayout(new GridLayout());
    this.add(mainPanel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(1500, 900);
    setLocationRelativeTo(null);
    setResizable(false);
    setVisible(true);

    addMenuPanel();
    addGamePanel();
  }

  private void addMenuPanel() {
    JPanel menuPanel = new JPanel(null);
    menuPanel.setBackground(new Color(21, 71, 52));

    JLabel title = new JLabel("Card (not game)", SwingConstants.CENTER);
    title.setFont(new Font("Serif", Font.BOLD, 80));
    title.setForeground(Color.white);
    title.setBounds(0, 100, 1500, 200);

    JLabel subTitle = new JLabel("All the game related ideas are scrapped", SwingConstants.CENTER);
    subTitle.setFont(new Font("Serif", Font.BOLD, 10));
    subTitle.setForeground(Color.white);
    subTitle.setBounds(0, 200, 1500, 200);

    JPanel optionPanel = new JPanel(new GridLayout(2, 1));
    JButton startButton = new JButton("Start");
    JButton exitButton = new JButton("Exit");
    startButton.addActionListener(listener);
    exitButton.addActionListener(listener);
    optionPanel.add(startButton);
    optionPanel.add(exitButton);
    optionPanel.setBounds(300, 300, 400, 200);
    optionPanel.setBounds(550, 400, 400, 200);

    menuPanel.add(title);
    menuPanel.add(subTitle);
    menuPanel.add(optionPanel);

    mainPanel.add(menuPanel, "menu");
  }

  private void addGamePanel() {
    JPanel gamePanel = new JPanel(new BorderLayout());

    addCard();

    cardsPanel.setBackground(new Color(21, 71, 52));
    JPanel optionPanel = new JPanel(new BorderLayout());

    JPanel cardNumberSettingPanel = getCardNumberSettingPanel();
    optionPanel.add(cardNumberSettingPanel, BorderLayout.NORTH);

    JPanel utilityPanel = getUtilityPanel();
    optionPanel.add(utilityPanel, BorderLayout.CENTER);

    JPanel settingPanel = getSettingPanel();
    optionPanel.add(settingPanel, BorderLayout.SOUTH);

    gamePanel.add(cardsPanel, BorderLayout.CENTER);
    gamePanel.add(optionPanel, BorderLayout.EAST);

    mainPanel.add(gamePanel, "game");
  }

  private JPanel getSettingPanel() {
    JPanel settingPanel = new JPanel(new GridLayout(1, 2));
    JButton backButton = new JButton("Back");
    JButton muteButton = new JButton("Mute");
    backButton.addActionListener(e -> cardLayout.next(mainPanel));
    muteButton.addActionListener(listener);
    settingPanel.add(backButton);
    settingPanel.add(muteButton);
    return settingPanel;
  }

  private JPanel getUtilityPanel() {
    JPanel utilityPanel = new JPanel(new GridLayout(1, 3));
    JButton shuffleButton = new JButton("Shuffle");
    JButton sortButton = new JButton("Sort");
    JButton drawButton = new JButton("Draw");
    shuffleButton.addActionListener(listener);
    sortButton.addActionListener(listener);
    drawButton.addActionListener(listener);
    utilityPanel.add(shuffleButton);
    utilityPanel.add(sortButton);
    utilityPanel.add(drawButton);
    return utilityPanel;
  }

  private JPanel getCardNumberSettingPanel() {
    JPanel cardNumberSettingPanel = new JPanel(new GridLayout(1, 3));
    JButton incButton = new JButton("+");
    JButton decButton = new JButton("-");
    incButton.addActionListener(listener);
    decButton.addActionListener(listener);

    cardNumberSettingPanel.add(decButton);
    cardNumberSettingPanel.add(cardNumberLabel);
    cardNumberSettingPanel.add(incButton);
    return cardNumberSettingPanel;
  }

  private void addCard() {
    List<Card> deck = random.getDeck();
    for (Card card : deck) {
      JLabel cardLabel = new JLabel();
      String cardURL = card.getCardImageURL();

      cardLabel.setIcon(getImageIcon(cardURL));
      cardsPanel.add(cardLabel);
    }
  }

  private ImageIcon getImageIcon(String cardURL) {
    ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource(cardURL));
    Image image = imageIcon.getImage();
    Image newImg =
        image.getScaledInstance(
            imageIcon.getIconWidth() / 3, imageIcon.getIconHeight() / 3, Image.SCALE_SMOOTH);
    return new ImageIcon(newImg);
  }

  public void drawCards() {
    random.drawCards();

    cardsPanel.removeAll();
    addCard();

    updateCardPanel();
  }

  public void shuffleDeck() {
    random.shuffleDeck();

    cardsPanel.removeAll();
    addCard();

    updateCardPanel();
  }

  public void sortDeck() {
    random.sortDeck(new b12.QuickSort<>());

    cardsPanel.removeAll();
    addCard();

    updateCardPanel();
  }

  public void toNextCard() { // change panel card, not card in deck
    cardLayout.next(mainPanel);
  }

  public void mute() {
    sound.stop();
  }

  public void playBackgroundMusic() {
    sound.playBackgroundMusic();
  }

  public void playEasterEgg() {
    if (cardNumberLabel.getText().equals("69")) sound.playEasterEgg();
  }

  public void increaseCardNumber() {
    random.n++;
    if (random.n > 52)
      JOptionPane.showMessageDialog(
          this, "You can't have more than 52 cards", "Error", JOptionPane.ERROR_MESSAGE);
    else {
      random.addCard();

      Card card = random.getDeck().get(random.n - 1);
      JLabel cardLabel = new JLabel();
      String cardURL = card.getCardImageURL();
      cardLabel.setIcon(getImageIcon(cardURL));
      cardsPanel.add(cardLabel);
      updateCardPanel();
    }
    cardNumberLabel.setText(random.n + "");
  }

  public void decreaseCardNumber() {
    if (random.n == 0) return;

    if (random.n <= 52) {
      random.n--;

      random.removeCard();
      cardsPanel.remove(random.n);

      updateCardPanel();
    } else random.n = 52;
    cardNumberLabel.setText(random.n + "");
  }

  public void updateCardPanel() {
    cardsPanel.revalidate();
    cardsPanel.repaint();
  }
}
