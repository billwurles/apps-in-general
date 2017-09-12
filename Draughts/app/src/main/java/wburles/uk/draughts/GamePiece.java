package wburles.uk.draughts;

public class GamePiece{

    private boolean selected = false;
    private boolean possibility = false;
    private int team; // 0-empty   1-white   2-black
    private boolean king;

    @Override
    public String toString(){
        return selected + " " + team + " " + king;
    }

    public GamePiece(int team){
        this.team = team;
    }

    public void select(){
            selected = true;
    }
    public void deselect(){selected=false;}
    public boolean isSelected(){
        return selected;
    }
    public void makePossibility(){possibility=true;}
    public void notPossibility(){possibility=false;}
    public boolean isPossibility(){
        return possibility;
    }
    public int getTeam() {          return team;    }
    public boolean isKing() {      return king;   }
    public void setKing(boolean k){this.king = k;}
}
