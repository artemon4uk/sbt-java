import handlers.Context;

public class MySerializer implements Serializer {
    private final Context context;

    public MySerializer(Context context) {
        this.context = context;
    }

    @Override
    public String serialize(Object o) {
        try {
            return context.executeStrategy(o);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "";
    }
}
