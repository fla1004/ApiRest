package factoryRequest;

public class FactoryRequest {
    public static final String GET ="get";
    public static final String POST ="post";
    public static final String PUT ="put";
    public static final String  DELETE ="delete";

    public static IRequest make (String typeRequest){
        IRequest request;

        switch (typeRequest){
            case "get":
                request  = new RequestGET();
                break;
            case "delete":
                request  = new RequestDELETE();
                break;
            case "post":
                request  = new RequestPOST();
                break;
            case "put":
                request  = new RequestPUT();
                break;
            default:
                request  = new RequestGET();
                break;

        }
        return request;
    }
}
