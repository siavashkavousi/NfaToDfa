import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths

/**
 * Created by sia on 10/26/15.
 */
class Parser(val path: String) {

    public fun readFile(): String {
        return String(Files.readAllBytes(Paths.get(path)), Charset.defaultCharset())
    }

    public fun parse() {
        val content = readFile()
        val lines: List<String>
        val items: MutableList<List<String>> = arrayListOf()
        lines = content.split("\r\n")
        for (line in lines) {
            if (line == "") continue
            items.add(line.split("\t"))
        }

        val states = parseStates(items)
        parseTransitions(states, items)
    }

    private fun parseStates(items: List<List<String>>): List<State> {
        var states: MutableList<State> = arrayListOf()
        for (item in items) {
            states.add(parseState(item[0]))
        }
        return states
    }

    private fun parseState(label: String): State {
        val transitions: MutableList<Transition> = arrayListOf()
        if (label.contains(">")) {
            return State(label.substring(1), transitions, true, false)
        } else if (label.contains("*")) {
            return State(label.substring(1), transitions, false, true)
        } else {
            return State(label, transitions, false, false)
        }
    }

    private fun parseTransitions(states: List<State>, items: List<List<String>>) {
        for (i in items.indices) {
            parseEachStateTransitions(states[i], states, items[i][1], 'b')
            parseEachStateTransitions(states[i], states, items[i][2], 'c')
        }
    }

    private fun parseEachStateTransitions(from: State, states: List<State>, items: String, character: Char) {
        var relatedStates = items.split(",")
        var state: State?
        for (relatedState in relatedStates) {
            state = findState(states, relatedState)
            if (state != null) from.addTransition(character, state)
        }
    }

    private fun findState(states: List<State>, label: String): State? {
        for (state in states) {
            if (state.label == label) {
                return state
            }
        }
        return null
    }
}