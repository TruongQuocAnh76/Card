package b3.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener implements ActionListener {
  private View view;

  public Listener(View view) {
    this.view = view;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JButton button = (JButton) e.getSource();
    String buttonText = button.getText();

    switch (buttonText) {
      case "Start", "Back" -> view.toNextCard();
      case "Exit" -> System.exit(0);
      case "+" -> {
        view.increaseCardNumber();
        view.playEasterEgg();
      }
      case "-" -> {
        view.decreaseCardNumber();
        view.playEasterEgg();
      }
      case "Shuffle" -> {
        view.shuffleDeck();
        view.updateCardPanel();
      }
      case "Sort" -> {
        view.sortDeck();
        view.updateCardPanel();
      }
      case "Draw" -> {
        view.drawCards();
        view.updateCardPanel();
      }
      case "Mute" -> {
        view.mute();
        JButton muteButton = (JButton) e.getSource();
        muteButton.setText("Unmute");
      }
      case "Unmute" -> {
        view.playBackgroundMusic();
        JButton unmuteButton = (JButton) e.getSource();
        unmuteButton.setText("Mute");
      }
    }
  }
}