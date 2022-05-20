package gui.game;

import gui.MainFrame;
import gui.Template;
import gui.game.components.displays.CoinsAmountDisplay;
import gui.game.components.DynamicComponent;
import gui.game.components.displays.DisplayLocation;
import gui.game.components.displays.PlayerNameDisplay;
import gui.game.components.hands.BotHand;
import gui.game.components.hands.HumanHand;
import gui.game.components.PauseButton;
import gui.game.components.RectangularContainer;
import gui.game.components.notifiers.LogNotifier;
import gui.guiconfig.game.components.*;
import gui.popup.ActionsPopup;
import gui.popup.HelpPopup;
import gui.guiutils.GeneralUtils;
import gui.guiutils.game.GamePanelUtils;
import gui.popup.LogPopup;
import logic.game.GameRunner;
import utils.resources.ImageIdentifier;
import utils.resources.ImageManager;

import javax.swing.*;
import java.util.ArrayList;

public class GamePanel extends Template implements DynamicPanel {
    private GameRunner gameRunner;

    private JLabel backgroundImage;
    private PauseButton pauseButton;
    private JButton helpButton;
    private JButton logButton;

    private RectangularContainer commandsContainer;
    private JButton actionsButton;
    private JButton challengeButton;
    private JButton passButton;

    private HumanHand humanHand;
    private BotHand leftBotHand;
    private BotHand topBotHand;
    private BotHand rightBotHand;

    private CoinsAmountDisplay humanCoinsDisplay;
    private CoinsAmountDisplay leftCoinsDisplay;
    private CoinsAmountDisplay topCoinsDisplay;
    private CoinsAmountDisplay rightCoinsDisplay;

    private PlayerNameDisplay leftNameDisplay;
    private PlayerNameDisplay topNameDisplay;
    private PlayerNameDisplay rightNameDisplay;

    private LogNotifier logNotifier;

    private ArrayList<DynamicComponent> dynamicComponentsList;

    public GamePanel(MainFrame mainFrame) {
        super(mainFrame);
        gameRunner = new GameRunner(this);

        drawPanel();
        addDynamicComponentsToList();

        Thread thread = new Thread(gameRunner, "Game Runner");
        thread.start();
    }

    @Override
    protected void initializeComponents() {
        ImageIcon backgroundImageIcon = new ImageIcon(ImageManager.getImage(ImageIdentifier.GAME_BACKGROUND));
        backgroundImage = new JLabel(backgroundImageIcon);

        pauseButton = new PauseButton(mainFrame, this);
        helpButton = new JButton("Help");
        logButton = new JButton("Log");

        commandsContainer = new RectangularContainer(CommandsContainerConfig.COMMAND_CONTAINER_WIDTH,
                CommandsContainerConfig.COMMAND_CONTAINER_HEIGHT);
        actionsButton = new JButton("Actions");
        challengeButton = new JButton("Challenge");
        passButton = new JButton("Pass");

        // these components will get aligned in their constructors:
        humanHand = new HumanHand(this);
        leftBotHand = new BotHand(this, BotHand.HandLocation.LEFT);
        topBotHand = new BotHand(this, BotHand.HandLocation.TOP);
        rightBotHand = new BotHand(this, BotHand.HandLocation.RIGHT);

        // these components, too, will get aligned in their constructors:
        humanCoinsDisplay = new CoinsAmountDisplay(this, DisplayLocation.BOTTOM);
        leftCoinsDisplay = new CoinsAmountDisplay(this, DisplayLocation.LEFT);
        topCoinsDisplay = new CoinsAmountDisplay(this, DisplayLocation.TOP);
        rightCoinsDisplay = new CoinsAmountDisplay(this, DisplayLocation.RIGHT);

        // these components, too, will get aligned in their constructors:
        leftNameDisplay = new PlayerNameDisplay(this, DisplayLocation.LEFT);
        topNameDisplay = new PlayerNameDisplay(this, DisplayLocation.TOP);
        rightNameDisplay = new PlayerNameDisplay(this, DisplayLocation.RIGHT);

        // these components, too, will get aligned in their constructors:
        logNotifier = new LogNotifier(this);
    }

    @Override
    protected void alignComponents() {
        pauseButton.setBounds(PauseButtonConfig.X_PAUSE_BUTTON, PauseButtonConfig.Y_PAUSE_BUTTON,
                PauseButtonConfig.PAUSE_BUTTON_SIZE, PauseButtonConfig.PAUSE_BUTTON_SIZE);
        add(pauseButton);

        helpButton.setBounds(HelpButtonConfig.X_HELP_BUTTON, HelpButtonConfig.Y_HELP_BUTTON,
                HelpButtonConfig.HELP_BUTTON_WIDTH, HelpButtonConfig.HELP_BUTTON_HEIGHT);
        add(helpButton);

        logButton.setBounds(LogButtonConfig.X_LOG_BUTTON, LogButtonConfig.Y_LOG_BUTTON,
                LogButtonConfig.LOG_BUTTON_WIDTH, LogButtonConfig.LOG_BUTTON_HEIGHT);
        add(logButton);

        GamePanelUtils.alignCommandsContainer(this, actionsButton, challengeButton, passButton,
                commandsContainer);

        GeneralUtils.alignBackground(this, backgroundImage);
    }

    @Override
    protected void connectListeners() {
        helpButton.addActionListener(e -> new HelpPopup());
        logButton.addActionListener(e -> new LogPopup());
        actionsButton.addActionListener(e -> new ActionsPopup(mainFrame, gameRunner.getStack()));
        passButton.addActionListener(e -> GamePanelUtils.passTurn());
    }

    private void addDynamicComponentsToList() {
        dynamicComponentsList = new ArrayList<>();

        dynamicComponentsList.add(humanHand);
        dynamicComponentsList.add(leftBotHand);
        dynamicComponentsList.add(topBotHand);
        dynamicComponentsList.add(rightBotHand);

        dynamicComponentsList.add(humanCoinsDisplay);
        dynamicComponentsList.add(leftCoinsDisplay);
        dynamicComponentsList.add(topCoinsDisplay);
        dynamicComponentsList.add(rightCoinsDisplay);

        dynamicComponentsList.add(logNotifier);
    }

    @Override
    public void updatePanel() {
        remove(backgroundImage);

        for (DynamicComponent component : dynamicComponentsList) {
            component.updateComponent();
        }

        GeneralUtils.alignBackground(this, backgroundImage);

        repaint();
        revalidate();
    }

    public void finishGameAndShowWinner() {
        EndgamePanel endgamePanel = new EndgamePanel(mainFrame);
        mainFrame.setCurrentPanel(endgamePanel);
    }
}
