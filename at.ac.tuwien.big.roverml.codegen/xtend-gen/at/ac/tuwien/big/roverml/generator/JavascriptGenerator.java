package at.ac.tuwien.big.roverml.generator;

import at.ac.tuwien.big.roverml.AngleUnit;
import at.ac.tuwien.big.roverml.Block;
import at.ac.tuwien.big.roverml.Command;
import at.ac.tuwien.big.roverml.Light;
import at.ac.tuwien.big.roverml.Move;
import at.ac.tuwien.big.roverml.Repeat;
import at.ac.tuwien.big.roverml.Rotate;
import at.ac.tuwien.big.roverml.Rover;
import at.ac.tuwien.big.roverml.RoverProgram;
import at.ac.tuwien.big.roverml.RoverSystem;
import at.ac.tuwien.big.roverml.SetLightColor;
import at.ac.tuwien.big.roverml.Terminate;
import at.ac.tuwien.big.roverml.Transition;
import at.ac.tuwien.big.roverml.TriggeredTransition;
import at.ac.tuwien.big.roverml.Wait;
import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class JavascriptGenerator /* implements IGenerator  */{
  private final static String FILENAME = "simulator";
  
  private final Map<Rover, Integer> roverId = CollectionLiterals.<Rover, Integer>newHashMap();
  
  private final Map<Light, String> lightId = CollectionLiterals.<Light, String>newHashMap();
  
  private final Map<Command, String> commandId = CollectionLiterals.<Command, String>newHashMap();
  
  public Integer getRoverId(final Rover rover) {
    final Function<Rover, Integer> _function = (Rover r) -> {
      return Integer.valueOf(this.roverId.size());
    };
    return this.roverId.computeIfAbsent(rover, _function);
  }
  
  public String getLightId(final Light light) {
    final Function<Light, String> _function = (Light it) -> {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("light_");
      int _size = this.lightId.size();
      _builder.append(_size);
      return _builder.toString();
    };
    return this.lightId.computeIfAbsent(light, _function);
  }
  
  public String getCommandId(final Command command) {
    final Function<Command, String> _function = (Command it) -> {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("command_");
      int _size = this.commandId.size();
      _builder.append(_size);
      return _builder.toString();
    };
    return this.commandId.computeIfAbsent(command, _function);
  }
  
  public float getHueAngle(final Rover rover) {
    Integer _roverId = this.getRoverId(rover);
    int _size = this.roverId.size();
    float _multiply = (_size * 1.0f);
    float _divide = ((_roverId).intValue() / _multiply);
    float _multiply_1 = (360.0f * _divide);
    Integer _roverId_1 = this.getRoverId(rover);
    return (_multiply_1 * (_roverId_1).intValue());
  }
  
  protected CharSequence _generateHtml(final RoverSystem roverSystem) {
    CharSequence _xblockexpression = null;
    {
      final Consumer<Rover> _function = (Rover r) -> {
        this.getRoverId(r);
      };
      roverSystem.getRovers().forEach(_function);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("<!DOCTYPE html>");
      _builder.newLine();
      _builder.append("<html lang=\"en\">");
      _builder.newLine();
      _builder.append("<head>");
      _builder.newLine();
      _builder.newLine();
      _builder.append("<meta name=\"description\" content=\"\">");
      _builder.newLine();
      _builder.append("<meta name=\"author\" content=\"\">");
      _builder.newLine();
      _builder.newLine();
      _builder.append("<meta charset=\"utf-8\">");
      _builder.newLine();
      _builder.append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
      _builder.newLine();
      _builder.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
      _builder.newLine();
      _builder.append("<title>RoverSystem Simulation</title>");
      _builder.newLine();
      _builder.newLine();
      _builder.append("<link href=\"bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">");
      _builder.newLine();
      _builder.append("<script src=\"bootstrap/js/jquery-3.3.1.min.js\"></script>");
      _builder.newLine();
      _builder.append("<script src=\"bootstrap/js/bootstrap.min.js\"></script>");
      _builder.newLine();
      _builder.append("<link href=\"lib/simulation.css\" rel=\"stylesheet\">");
      _builder.newLine();
      _builder.append("<script src=\"lib/simulation.js\"></script>");
      _builder.newLine();
      _builder.append("<script src=\"");
      _builder.append(JavascriptGenerator.FILENAME);
      _builder.append(".js\"></script>");
      _builder.newLineIfNotEmpty();
      _builder.append("</head>");
      _builder.newLine();
      _builder.newLine();
      _builder.append("<body onload=\"drawOnLoad();\">");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("<nav class=\"navbar navbar-inverse navbar-fixed-top\">");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("<div class=\"container\">");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("<div class=\"navbar-header\">");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("<button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#navbar\" aria-expanded=\"false\" aria-controls=\"navbar\">");
      _builder.newLine();
      _builder.append("\t\t\t\t\t");
      _builder.append("<span class=\"sr-only\">Toggle navigation</span>");
      _builder.newLine();
      _builder.append("\t\t\t\t\t");
      _builder.append("<span class=\"icon-bar\"></span>");
      _builder.newLine();
      _builder.append("\t\t\t\t\t");
      _builder.append("<span class=\"icon-bar\"></span>");
      _builder.newLine();
      _builder.append("\t\t\t\t\t");
      _builder.append("<span class=\"icon-bar\"></span>");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("</button>");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("<a class=\"navbar-brand\">RoverSystem Simulation</a>");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("</div>");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("<div id=\"navbar\" class=\"collapse navbar-collapse\">");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("<ul class=\"nav navbar-nav\">");
      _builder.newLine();
      _builder.append("\t\t\t\t\t");
      _builder.append("<li><a onclick=\"runSimulation();\">Run</a></li>");
      _builder.newLine();
      _builder.append("\t\t\t\t\t");
      _builder.append("<li><a onclick=\"step();\">Next step</a></li>");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("</ul>");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("</div>");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("</div>");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("</nav>");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("<div class=\"page-header\">");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("<br/>");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("<div class=\"container\">");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("<div class=\"row\">");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("<div class=\"col-sm-4\">");
      _builder.newLine();
      _builder.append("\t\t\t\t\t");
      _builder.append("<div id=\"roversImages\" class=\"panel panel-primary\">");
      _builder.newLine();
      _builder.append("\t\t\t\t\t\t");
      _builder.append("<div class=\"panel-heading\">Rovers</div>");
      _builder.newLine();
      _builder.append("\t\t\t\t\t\t");
      _builder.append("<div class=\"panel-body\">");
      _builder.newLine();
      _builder.append("\t\t\t\t\t\t\t");
      _builder.newLine();
      {
        EList<Rover> _rovers = roverSystem.getRovers();
        for(final Rover rover : _rovers) {
          _builder.append("\t\t\t\t\t\t\t");
          _builder.append("<div class=\"col-sm-6\">");
          _builder.newLine();
          _builder.append("\t\t\t\t\t\t\t");
          _builder.append("\t");
          _builder.append("<h3>");
          String _name = rover.getName();
          _builder.append(_name, "\t\t\t\t\t\t\t\t");
          _builder.append("</h3>");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t\t\t\t\t\t");
          _builder.append("\t");
          _builder.append("<img src=\"img/rover1.gif\" id=\"rover_image_");
          Integer _roverId = this.getRoverId(rover);
          _builder.append(_roverId, "\t\t\t\t\t\t\t\t");
          _builder.append("\" width=\"50\" style=\"filter:hue-rotate(");
          float _hueAngle = this.getHueAngle(rover);
          _builder.append(_hueAngle, "\t\t\t\t\t\t\t\t");
          _builder.append("deg)\"/>");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t\t\t\t\t\t");
          _builder.append("</div>");
          _builder.newLine();
          _builder.append("\t\t\t\t\t\t\t");
          _builder.newLine();
        }
      }
      _builder.append("\t\t\t\t\t\t");
      _builder.append("</div> ");
      _builder.newLine();
      _builder.append("\t\t\t\t\t");
      _builder.append("</div>");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("</div>");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("<div class=\"col-sm-8\">");
      _builder.newLine();
      _builder.append("\t\t\t\t\t");
      _builder.append("<div class=\"panel panel-primary\">");
      _builder.newLine();
      _builder.append("\t\t\t\t\t\t");
      _builder.append("<div class=\"panel-heading\"><span style=\"font-size:16px;\">Simulation canvas</span></div>");
      _builder.newLine();
      _builder.append("\t\t\t\t\t\t");
      _builder.append("<div class=\"panel-body\" id=\"simulationDiv\"><canvas id=\"simulationCanvas\" width=\"700\" height=\"300\"></canvas></div>");
      _builder.newLine();
      _builder.append("\t\t\t\t\t");
      _builder.append("</div>");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("</div>");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("</div>");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("</div>");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("<div class=\"container\">");
      _builder.newLine();
      {
        EList<RoverProgram> _roverPrograms = roverSystem.getRoverPrograms();
        for(final RoverProgram program : _roverPrograms) {
          _builder.append("\t\t\t");
          _builder.append("<div class=\"col-sm-3\">");
          _builder.newLine();
          _builder.append("\t\t\t");
          _builder.append("\t");
          Object _generateHtml = this.generateHtml(program.getRover());
          _builder.append(_generateHtml, "\t\t\t\t");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t\t");
          _builder.append("\t");
          _builder.newLine();
          _builder.append("\t\t\t");
          _builder.append("\t");
          Object _generateHtml_1 = this.generateHtml(program);
          _builder.append(_generateHtml_1, "\t\t\t\t");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t\t");
          _builder.append("</div>");
          _builder.newLine();
          _builder.append("\t\t\t");
          _builder.newLine();
        }
      }
      _builder.append("\t\t");
      _builder.append("</div>");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("</div>");
      _builder.newLine();
      _builder.append("</body>");
      _builder.newLine();
      _builder.append("</html>");
      _builder.newLine();
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  protected CharSequence _generateHtml(final Rover rover) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<div class=\"panel panel-success\">");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<div class=\"panel-heading\">Lights of ");
    String _name = rover.getName();
    _builder.append(_name, "\t");
    _builder.append("</div>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<div class=\"panel-body\">");
    _builder.newLine();
    {
      Iterable<Light> _filter = Iterables.<Light>filter(rover.getComponents(), Light.class);
      for(final Light light : _filter) {
        _builder.append("\t\t");
        _builder.append("<span id=\"");
        String _lightId = this.getLightId(light);
        _builder.append(_lightId, "\t\t");
        _builder.append("\" class=\"light off\">&ensp;&ensp;</span>");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("</div> ");
    _builder.newLine();
    _builder.append("</div>");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence _generateHtml(final RoverProgram roverProgram) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<div class=\"panel panel-danger\">");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<div class=\"panel-heading\">");
    String _name = roverProgram.getName();
    _builder.append(_name, "\t");
    _builder.append("</div>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    Object _generateHtml = this.generateHtml(roverProgram.getBlock());
    _builder.append(_generateHtml, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("</div>");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence _generateHtml(final Block block) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<ul class=\"list-group\">");
    _builder.newLine();
    {
      EList<Command> _commands = block.getCommands();
      for(final Command command : _commands) {
        _builder.append("\t");
        Object _generateHtml = this.generateHtml(command);
        _builder.append(_generateHtml, "\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("</ul>");
    _builder.newLine();
    return _builder;
  }
  
  protected String _generateHtml(final Repeat repeat) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<li class=\"list-group-item\">");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<h4>repeat ");
    int _count = repeat.getCount();
    _builder.append(_count, "\t");
    _builder.append(" times</h4>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<ul class=\"list-group\">");
    _builder.newLine();
    {
      EList<Command> _commands = repeat.getCommands();
      for(final Command command : _commands) {
        _builder.append("\t\t");
        CharSequence _generateHtml = this.generateHtml(command);
        _builder.append(_generateHtml, "\t\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("</ul>");
    _builder.newLine();
    _builder.append("</li>");
    _builder.newLine();
    return _builder.toString();
  }
  
  protected CharSequence _generateHtml(final Command command) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<li id=\"");
    String _commandId = this.getCommandId(command);
    _builder.append(_commandId);
    _builder.append("\" class=\"list-group-item\">");
    _builder.newLineIfNotEmpty();
    {
      if (((command.getName() == null) || command.getName().isEmpty())) {
        _builder.append("\t");
        String _uRIFragment = command.eResource().getURIFragment(command);
        _builder.append(_uRIFragment, "\t");
        _builder.newLineIfNotEmpty();
      } else {
        _builder.append("\t");
        String _name = command.getName();
        _builder.append(_name, "\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("</li>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateJavascript(final RoverSystem roverSystem) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("//These variable stores the successive positions of rovers");
    _builder.newLine();
    _builder.append("// xPos[0][0] = the 1st X position of rover 0");
    _builder.newLine();
    _builder.append("// yPos[1][3] = the 4th Y position of rover 1");
    _builder.newLine();
    _builder.append("var xPos = [");
    {
      EList<Rover> _rovers = roverSystem.getRovers();
      boolean _hasElements = false;
      for(final Rover rover : _rovers) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(",", "");
        }
        _builder.append("[0]");
      }
    }
    _builder.append("];");
    _builder.newLineIfNotEmpty();
    _builder.append("var yPos = [");
    {
      int _size = roverSystem.getRovers().size();
      int _minus = (_size - 1);
      IntegerRange _upTo = new IntegerRange(0, _minus);
      boolean _hasElements_1 = false;
      for(final Integer i : _upTo) {
        if (!_hasElements_1) {
          _hasElements_1 = true;
        } else {
          _builder.appendImmediate(",", "");
        }
        _builder.append("[");
        _builder.append((10 + (70 * (i).intValue())));
        _builder.append("]");
      }
    }
    _builder.append("];");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("//These variables are used to know in which direction the rovers have to move");
    _builder.newLine();
    _builder.append("//Init them to 0;");
    _builder.newLine();
    {
      EList<Rover> _rovers_1 = roverSystem.getRovers();
      for(final Rover rover_1 : _rovers_1) {
        _builder.append("var rover");
        Integer _roverId = this.getRoverId(rover_1);
        _builder.append(_roverId);
        _builder.append("angle = 0;");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    _builder.append("//This variable stores the number of next executed command");
    _builder.newLine();
    _builder.append("var nextStep = 0;");
    _builder.newLine();
    _builder.newLine();
    {
      EList<RoverProgram> _roverPrograms = roverSystem.getRoverPrograms();
      for(final RoverProgram program : _roverPrograms) {
        CharSequence _generateStepJavascript = this.generateStepJavascript(program);
        _builder.append(_generateStepJavascript);
        _builder.newLineIfNotEmpty();
        _builder.newLine();
      }
    }
    _builder.newLine();
    _builder.append("function drawOnLoad(){");
    _builder.newLine();
    {
      EList<Rover> _rovers_2 = roverSystem.getRovers();
      for(final Rover rover_2 : _rovers_2) {
        _builder.append("\t");
        _builder.append("drawRover(");
        Integer _roverId_1 = this.getRoverId(rover_2);
        _builder.append(_roverId_1, "\t");
        _builder.append(");");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    {
      EList<RoverProgram> _roverPrograms_1 = roverSystem.getRoverPrograms();
      for(final RoverProgram program_1 : _roverPrograms_1) {
        CharSequence _generateRunJavascript = this.generateRunJavascript(program_1);
        _builder.append(_generateRunJavascript);
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    _builder.append("function runSimulation(){");
    _builder.newLine();
    {
      EList<RoverProgram> _roverPrograms_2 = roverSystem.getRoverPrograms();
      for(final RoverProgram program_2 : _roverPrograms_2) {
        _builder.append("\t");
        String _firstLower = StringExtensions.toFirstLower(program_2.getName().replaceAll(" ", ""));
        _builder.append(_firstLower, "\t");
        _builder.append("();");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("nextStep = Math.max(");
    {
      EList<RoverProgram> _roverPrograms_3 = roverSystem.getRoverPrograms();
      boolean _hasElements_2 = false;
      for(final RoverProgram program_3 : _roverPrograms_3) {
        if (!_hasElements_2) {
          _hasElements_2 = true;
        } else {
          _builder.appendImmediate(",", "\t");
        }
        String _replaceAll = program_3.getRover().getName().replaceAll(" ", "");
        _builder.append(_replaceAll, "\t");
        _builder.append("_commands.length");
      }
    }
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("function step(){");
    _builder.newLine();
    _builder.newLine();
    {
      EList<RoverProgram> _roverPrograms_4 = roverSystem.getRoverPrograms();
      for(final RoverProgram program_4 : _roverPrograms_4) {
        _builder.append("\t");
        _builder.append("if(nextStep <= ");
        String _replaceAll_1 = program_4.getRover().getName().replaceAll(" ", "");
        _builder.append(_replaceAll_1, "\t");
        _builder.append("_commands.length - 1){");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        String _replaceAll_2 = program_4.getRover().getName().replaceAll(" ", "");
        _builder.append(_replaceAll_2, "\t\t");
        _builder.append("_commands[nextStep].call();");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.append("nextStep++;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public List<Command> flattenCommands(final Block block) {
    final List<Command> result = CollectionLiterals.<Command>newArrayList();
    final Consumer<Command> _function = (Command c) -> {
      if ((c instanceof Repeat)) {
        result.addAll(this.flattenCommands(((Block)c)));
      } else {
        result.add(c);
      }
    };
    block.getCommands().forEach(_function);
    if ((block instanceof Repeat)) {
      final List<Command> repeatCommands = CollectionLiterals.<Command>newArrayList(((Command[])Conversions.unwrapArray(result, Command.class)));
      for (int i = 0; (i < (((Repeat)block).getCount() - 1)); i++) {
        result.addAll(repeatCommands);
      }
    }
    final Function1<Command, Boolean> _function_1 = (Command c) -> {
      return Boolean.valueOf((c instanceof Terminate));
    };
    final Command terminate = IterableExtensions.<Command>findFirst(result, _function_1);
    if ((terminate != null)) {
      return result.subList(0, result.indexOf(terminate));
    }
    return result;
  }
  
  public CharSequence generateStepJavascript(final RoverProgram roverProgram) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("var ");
    String _replaceAll = roverProgram.getRover().getName().replaceAll(" ", "");
    _builder.append(_replaceAll);
    _builder.append("_commands =[");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    final List<Command> commands = this.flattenCommands(roverProgram.getBlock());
    _builder.newLineIfNotEmpty();
    {
      int _size = commands.size();
      int _minus = (_size - 1);
      IntegerRange _upTo = new IntegerRange(0, _minus);
      boolean _hasElements = false;
      for(final Integer i : _upTo) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(",", "\t\t");
        }
        _builder.append("\t\t");
        final Command command = commands.get((i).intValue());
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("()=>{");
        CharSequence _generateStepJavascript = this.generateStepJavascript(command, (this.getRoverId(roverProgram.getRover())).intValue());
        _builder.append(_generateStepJavascript, "\t\t");
        {
          if (((i).intValue() > 0)) {
            _builder.append("unHighlight(\"");
            String _commandId = this.getCommandId(commands.get(((i).intValue() - 1)));
            _builder.append(_commandId, "\t\t");
            _builder.append("\");");
          }
        }
        _builder.append("highlight(\"");
        String _commandId_1 = this.getCommandId(command);
        _builder.append(_commandId_1, "\t\t");
        _builder.append("\");}");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("];");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence _generateStepJavascript(final Move move, final int roverId) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("moveRover(");
    _builder.append(roverId);
    _builder.append(",rover");
    _builder.append(roverId);
    _builder.append("angle,");
    float _value = move.getDistance().getValue();
    float _multiply = (_value * 10);
    _builder.append(_multiply);
    _builder.append(");");
    return _builder;
  }
  
  protected CharSequence _generateStepJavascript(final Rotate rotate, final int roverId) {
    CharSequence _xblockexpression = null;
    {
      float _xifexpression = (float) 0;
      AngleUnit _angleUnit = rotate.getAngle().getAngleUnit();
      boolean _equals = Objects.equal(_angleUnit, AngleUnit.DEGREE);
      if (_equals) {
        double _radians = Math.toRadians(rotate.getAngle().getValue());
        _xifexpression = ((float) _radians);
      } else {
        _xifexpression = rotate.getAngle().getValue();
      }
      final float angle = _xifexpression;
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("rover");
      _builder.append(roverId);
      _builder.append("angle=rotate(rover");
      _builder.append(roverId);
      _builder.append("angle,");
      _builder.append(angle);
      _builder.append(");");
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  protected CharSequence _generateStepJavascript(final Wait wait, final int roverId) {
    return "";
  }
  
  protected CharSequence _generateStepJavascript(final SetLightColor setLightColor, final int roverId) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<Light> _lights = setLightColor.getLights();
      for(final Light light : _lights) {
        _builder.append("setLight(\"");
        String _lightId = this.getLightId(light);
        _builder.append(_lightId);
        _builder.append("\",\"");
        String _lowerCase = setLightColor.getColor().getLiteral().toLowerCase();
        _builder.append(_lowerCase);
        _builder.append("\");");
      }
    }
    return _builder;
  }
  
  protected CharSequence _generateStepJavascript(final Terminate terminate, final int roverId) {
    return "";
  }
  
  public CharSequence generateCommandHighlighting(final Command command) {
    CharSequence _xblockexpression = null;
    {
      final Function1<Transition, Boolean> _function = (Transition t) -> {
        return Boolean.valueOf((!(t instanceof TriggeredTransition)));
      };
      Transition _findFirst = IterableExtensions.<Transition>findFirst(command.getIncoming(), _function);
      Command _source = null;
      if (_findFirst!=null) {
        _source=_findFirst.getSource();
      }
      final Command previousCommand = _source;
      StringConcatenation _builder = new StringConcatenation();
      {
        if ((previousCommand != null)) {
          _builder.append("unHighlight(\"");
          String _commandId = this.getCommandId(previousCommand);
          _builder.append(_commandId);
          _builder.append("\");");
        }
      }
      _builder.append("highlight(\"");
      String _commandId_1 = this.getCommandId(command);
      _builder.append(_commandId_1);
      _builder.append("\");");
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  private int freshVarNumber = 0;
  
  public CharSequence generateRunJavascript(final RoverProgram roverProgram) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("function ");
    String _firstLower = StringExtensions.toFirstLower(roverProgram.getName().replaceAll(" ", ""));
    _builder.append(_firstLower);
    _builder.append("() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _generateRunJavascript = this.generateRunJavascript(roverProgram.getBlock(), (this.getRoverId(roverProgram.getRover())).intValue());
    _builder.append(_generateRunJavascript, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence _generateRunJavascript(final Block block, final int roverId) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<Command> _commands = block.getCommands();
      for(final Command command : _commands) {
        Object _generateRunJavascript = this.generateRunJavascript(command, roverId);
        _builder.append(_generateRunJavascript);
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  protected CharSequence _generateRunJavascript(final Repeat repeat, final int roverId) {
    final String fresh = ("i" + Integer.valueOf(this.freshVarNumber));
    this.freshVarNumber++;
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("for (var ");
    _builder.append(fresh);
    _builder.append(" = ");
    int _count = repeat.getCount();
    _builder.append(_count);
    _builder.append("; ");
    _builder.append(fresh);
    _builder.append(" > 0; ");
    _builder.append(fresh);
    _builder.append("--) {");
    _builder.newLineIfNotEmpty();
    {
      EList<Command> _commands = repeat.getCommands();
      for(final Command command : _commands) {
        _builder.append("\t");
        Object _generateRunJavascript = this.generateRunJavascript(command, roverId);
        _builder.append(_generateRunJavascript, "\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("}");
    _builder.newLine();
    final String result = _builder.toString();
    this.freshVarNumber--;
    return result;
  }
  
  protected CharSequence _generateRunJavascript(final Command command, final int roverId) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _generateStepJavascript = this.generateStepJavascript(command, roverId);
    _builder.append(_generateStepJavascript);
    return _builder;
  }
  
  public static void main(final String[] args) {
    throw new Error("Unresolved compilation problems:"
      + "\nSimulatorGenerator cannot be resolved."
      + "\ngenerateHtml cannot be resolved"
      + "\ngenerateJavascript cannot be resolved"
      + "\ntoString cannot be resolved"
      + "\nbytes cannot be resolved"
      + "\ntoString cannot be resolved"
      + "\nbytes cannot be resolved");
  }
  
  public CharSequence generateHtml(final EObject repeat) {
    if (repeat instanceof Repeat) {
      return _generateHtml((Repeat)repeat);
    } else if (repeat instanceof Command) {
      return _generateHtml((Command)repeat);
    } else if (repeat instanceof Rover) {
      return _generateHtml((Rover)repeat);
    } else if (repeat instanceof RoverProgram) {
      return _generateHtml((RoverProgram)repeat);
    } else if (repeat instanceof Block) {
      return _generateHtml((Block)repeat);
    } else if (repeat instanceof RoverSystem) {
      return _generateHtml((RoverSystem)repeat);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(repeat).toString());
    }
  }
  
  public CharSequence generateStepJavascript(final Command move, final int roverId) {
    if (move instanceof Move) {
      return _generateStepJavascript((Move)move, roverId);
    } else if (move instanceof Rotate) {
      return _generateStepJavascript((Rotate)move, roverId);
    } else if (move instanceof SetLightColor) {
      return _generateStepJavascript((SetLightColor)move, roverId);
    } else if (move instanceof Terminate) {
      return _generateStepJavascript((Terminate)move, roverId);
    } else if (move instanceof Wait) {
      return _generateStepJavascript((Wait)move, roverId);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(move, roverId).toString());
    }
  }
  
  public CharSequence generateRunJavascript(final EObject repeat, final int roverId) {
    if (repeat instanceof Repeat) {
      return _generateRunJavascript((Repeat)repeat, roverId);
    } else if (repeat instanceof Command) {
      return _generateRunJavascript((Command)repeat, roverId);
    } else if (repeat instanceof Block) {
      return _generateRunJavascript((Block)repeat, roverId);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(repeat, roverId).toString());
    }
  }
}
