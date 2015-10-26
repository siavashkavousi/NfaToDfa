/**
 * Created by sia on 10/26/15.
 */
class Transition(private val from: State, private val to: State, private val character: Char) {
    override fun toString(): String {
        return "$from=> $character =>$to";
    }
}
