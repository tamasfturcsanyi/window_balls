# Window Balls - User Manual

## Brief Overview

This is a ball simulation program. The program simulates balls in 2D as they collide with each other and bounce off the window walls.

The program operates in three modes:

1. **Simulation**
2. **Basket**
3. **Volley**

## Detailed Operation

### Main Menu

You can try the simulation here. If you move the window on your screen, you will see that the labels and buttons stay in place, and the balls are pushed by the window movement. The buttons also participate in the simulation, and the balls bounce off them. You can access the three modes with the three buttons. To exit, just close the window. This is true for the rest of the program as well.

---

### Simulation

#### Simulation Picker

You can open simulations through the Simulation Picker. The Simulation Picker consists of two parts: buttons at the top and simulations below.

The simulations are in a scrollable panel. If you click on one, you select it. If you click again or select another simulation, you deselect the originally selected simulation.

There are four buttons in the button section. Their functions from left to right:

1. **Add**: Creates and opens a new, empty simulation with default settings.
2. **Open**: Opens an existing simulation. It can only be pressed if a simulation is selected beforehand.
3. **Delete**: Deletes the selected simulation. It can only be pressed if a simulation is selected beforehand.
4. **Back**: Closes the Simulation Picker and opens the Main Menu.

---

#### Simulation Player

In the Simulation Player, you can create, modify, and play different simulations. The simulation is visible on the bottom panel. The top panel controls it. The Simulation Player has two states: Running and Stopped.

- **In Stopped mode**:
    - If you move the window, the balls do not move on the screen.
    - You can freely resize the window.
    - If you click on a participant in the simulation, you select it. Only one participant can be selected at a time.
    - While a participant is selected, it follows the mouse movement. This way, you can move participants in the simulation.
    - You can delete the selected participant by pressing the `Delete` key.
    - You can copy the selected participant with the `Ctrl` + `C` combination.

- **In Running mode**:
    - The balls react to window movement.
    - You cannot resize the window.

The buttons in the top row are all usable in Stopped mode. Here is their functionality from left to right:

1. **Save**: Usable only in Stopped mode. Opens the Save Dialog. Here you can choose to save the simulation to the opened file or a new one. If you choose the new one, you must provide a name for the simulation. If you provide a name that already exists, it will overwrite that file. The `Cancel` button discards the save and returns to the Simulation Player. The `OK` button can only be pressed if a file to save to is provided. Pressing it performs the actual save.
2. **Add**: Usable only in Stopped mode. Opens the Create Body Dialog. Here you can specify a participant with text fields and comboboxes. The `Create` button can only be pressed with correctly provided values. Pressing it closes the Dialog and adds the new participant to the simulation. If you close the Dialog, the addition is discarded.
3. **Slow Down**: Decreases the simulation speed by 0.25. It does not allow going below 0. The simulation speed is visible in a Label in the same row. Usable in Running mode as well.
4. **Start/Stop**: Switches between Stopped and Running mode. Starts or stops the simulation.
5. **Speed Up**: Same as Slow Down, but increases by 0.25.
6. **Settings**: Usable only in Stopped mode. Here you can modify the simulation parameters through a Dialog. The `Cancel` button discards the changes and returns to the Simulation Player. The `OK` button can only be pressed if all parameters are correctly provided. Pressing it performs the actual save.
7. **Back**: Closes the Simulation Player and returns to the Simulation Picker. Usable in both Stopped and Running mode.

---

### Basket

An arcade-style game where you need to break the record.

You need to perform as many dunks as possible with the orange ball within the time limit. You can manipulate the ball by moving the window on the screen. Somewhere on the screen, there is a "Backboard" consisting of two fixed balls. If you move the basketball down through the backboard, you score a dunk. Each dunk adds some time to your remaining time and gives you a point. The game ends when your remaining time runs out.

The remaining time is displayed prominently on the screen. Below it is your current score. The background color changes according to the remaining time.

When the game ends, you can see your score and the current record. You can choose to restart the game or return to the main menu.

---

### Volley

#### Goal

This is a two-player game reminiscent of volleyball. The two players play against each other. A wall divides the playing field into two parts. The goal is to reach the set score first. You score a point when the ball lands on your opponent's side. You control a live ball on your side to prevent the ball from landing on your side.

#### Controls

One player uses WASD, and the other uses the arrow keys to move. The up key allows you to jump, but not very high. To reach the real height, you need to use the fact that you are also a ball. The down key allows you to bounce yourself. You can pause the game by pressing the `Escape` key. When paused, two buttons appear. `Restart` restarts the match with the same settings. `Exit` returns you to the Main Menu. Pressing `Escape` again resumes the game. If one player wins, you will see the same buttons.

#### Setup

Before starting the game, you enter the Volley Starter. Here you can set the color and face of your ball. Additionally, you can set the number of points for the match. Pressing the Start button begins the match.