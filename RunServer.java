import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import java.io.File;

public class RunServer {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        // Get the WAR file
        File warFile = new File("target/marathon.war");
        if (!warFile.exists()) {
            System.err.println("WAR file not found! Please run 'mvn clean package' first.");
            System.exit(1);
        }

        // Deploy the WAR file
        Context context = tomcat.addWebapp("/marathon", warFile.getAbsolutePath());

        System.out.println("Starting Tomcat server on http://localhost:8080/marathon");
        System.out.println("Press Ctrl+C to stop the server");

        tomcat.start();
        tomcat.getServer().await();
    }
}