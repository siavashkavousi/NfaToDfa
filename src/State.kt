/**
 * Created by sia on 10/26/15.
 */
class State(val label: String, val transitions: MutableList<Transition>, var isStart: Boolean, var isEnd : Boolean) {
    public fun addTransition(character: Char, to: State) {
        transitions.add(Transition(this, to, character))
    }
}