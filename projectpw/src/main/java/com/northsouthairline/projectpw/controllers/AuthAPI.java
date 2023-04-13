import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;



@Path("/")
public class AuthAPI {

    private static final String USERNAME = "usuario";
    private static final String PASSWORD = "password";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";
    private static final Map<String, String> users = new HashMap<>();

    static {
        users.put(USERNAME, PASSWORD);
    }

    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login(@HeaderParam(AUTHORIZATION_HEADER) String authHeader) {
        if (authHeader == null || !authHeader.startsWith(AUTHENTICATION_SCHEME)) {
            throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED).build());
        }
        String[] authTokens = extractAuthTokens(authHeader);
        String username = authTokens[0];
        String password = authTokens[1];
        if (authenticate(username, password)) {
            String token = generateToken();
            return Response.ok(new Token(token)).build();
        } else {
            throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private boolean authenticate(String username, String password) {
        String storedPassword = users.get(username);
        return password.equals(storedPassword);
    }

    private String[] extractAuthTokens(String authHeader) {
        String encodedTokens = authHeader.substring(AUTHENTICATION_SCHEME.length()).trim();
        byte[] decodedBytes = Base64.getDecoder().decode(encodedTokens);
        String decodedTokens = new String(decodedBytes);
        return decodedTokens.split(":");
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }

    private static class Token {
        private String token;

        public Token(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}

