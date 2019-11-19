import com.avyay.json.JSON;

public class Test {
    public static void main(String[] args)
    {
        JSON.Object a = JSON.parse("{" +
                "\"a\":{" +
                "\"b\":4}" +
                "}").asObject();
        System.out.println(a.get("a").asObject().get("b").asInt().value());
    }
}
