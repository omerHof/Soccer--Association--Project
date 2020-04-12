package LeagueSeasonsManagment;

import Games.Game;
import Teams.Team;

import java.util.ArrayList;
import java.util.HashMap;

public interface IGameInlayPolicy {


    HashMap<Integer, ArrayList<Game>> gameInlayPolicyAlgoImplementation();
    String getName();
}
