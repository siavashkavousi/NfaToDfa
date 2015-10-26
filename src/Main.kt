/**
 * Created by sia on 10/26/15.
 */
object Main {
    @JvmStatic fun main(args: Array<String>) {
        val parser = Parser("NDFA.txt")
        parser.parse()
    }
}
