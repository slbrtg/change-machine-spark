import java.util.HashMap;
import java.util.Map;
import java.lang.Float;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {

  public static void main(String[] args) {
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/change", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String dollarAmount = request.queryParams("dollar-amount");
      float floatDollarAmount = Float.valueOf(dollarAmount);
      ChangeMachine runningChangeMachine = new ChangeMachine();
      model.put("dollar-amount", runningChangeMachine.makeChange(floatDollarAmount));
      model.put("template", "templates/change.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
