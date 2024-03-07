package usopshiy.web4.database;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import usopshiy.web4.entity.Point;
import usopshiy.web4.entity.User;
import java.util.List;
import java.util.StringJoiner;

@Stateless
public class Points {
    @EJB
    private DBHandler dbHandler;

    public String addPoint(String email, String x, String y, String r) {
        User user = dbHandler.getUser(email);
        try {
            double xd = Double.parseDouble(x);
            double yd = Double.parseDouble(y);
            Integer rd = Integer.parseInt(r);
            if (-3 <= xd && xd <= 5 && -3 <= yd && yd <= 5 && -3 <= rd && rd <= 50) {
                dbHandler.addPoint(dbHandler.createPoint(xd, yd, rd, user));
            }
        } catch (NumberFormatException e){
            return "Bad format";
        }
        return getPoints(email);
    }

    public String getPoints(String email){
        User user = dbHandler.getUser(email);

        List<Point> points = dbHandler.getPoints(user);
        StringJoiner joiner = new StringJoiner(",");
        for (Point point : points){
            String stringBuilder = "{\"x\":\"" +
                    point.getX() +
                    "\", \"y\":\"" +
                    point.getY() +
                    "\", \"r\":\"" +
                    point.getR() +
                    "\", \"isHit\":\"" +
                    point.isHit() +
                    "\"}";
            joiner.add(stringBuilder);
        }

        return "["+joiner.toString()+"]";
    }

    public String clearPoints(String email){
        User user = dbHandler.getUser(email);

        return String.valueOf(dbHandler.clearPoints(user));
    }
}
