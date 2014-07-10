/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgame.Models;

/**
 *
 * @author devinbost
 * 
 */
/**
 * This enum is designed to indicate the type of event used (e.g. event used to update a control's display text).
 */

public enum EventTypeEnum {
    /**
     * This event is when the gamer runs out of time.
     */
    CountdownOutOfTime,
    /**
     * This event is when the timer's value is updated.
     */
    CountdownTick,
    /**
     * This event is when the gamer's score changes.
     */
    ScoreUpdate,
    /**
     * This event is raised when gamer's number of lives change.
     */
    RemainingLivesUpdate,
    /**
     * NPC = Non-Playable Character. This event is when an NPC speaks to gamer.
     */
    NpcDialogueUpdate,
    /**
     * When gamer requests next question.
     */
    NextQuestion,
    /**
     * When gamer runs out of lives or game ends.
     */
    GameOver,
    /**
     * This event is when a gamer answers a question.
     */
    GamerAnswerSelected,
    // ComputerTerminalActivated,This event needs to involve a different ScreenControl
    /**
     * This event indicates when the cannon is fired. Note: If cannonball misses target but the gamer's answer is correct, be sure to record it as correct after a certain amount of time passes (to give sufficient time for the cannonball to hit the target but not make the gamer wait forever due to a graphical or physics bug).
     */
    CannonFired
}
