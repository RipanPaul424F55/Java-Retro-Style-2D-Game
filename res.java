public class res
{
    public static void main(String args[])
    {
        int gp_maxworldcol=50;
        int gp_maxworldrow=50;
        int gp_tilesize=48;
        int gp_player_worldx=1104;
        int gp_player_worldy=1008;
        int gp_player_screenx=384;
        int gp_player_screeny=288;


        int worldcol=0;
        int worldrow=0;
        int count=1;
        while(worldcol<gp_maxworldcol && worldrow<gp_maxworldrow)
        {
            int worldx=worldcol*gp_tilesize;
            int worldy=worldrow*gp_tilesize;
            int screenx=worldx-gp_player_worldx+gp_player_screenx;
            int screeny=worldy-gp_player_worldy+gp_player_screeny;
            if(screenx>=0 && screenx<768 && screeny>=0 && screeny<576)
            System.out.println("Visible coordinates ("+screenx+","+screeny+") worldrow,worldcol("+worldrow+","+worldcol+")");
           // else
           // System.out.println("coordinates ("+screenx+","+screeny+")");
            worldcol++;
            if(worldcol==gp_maxworldcol-1)
            {
                System.out.println("---------row "+(count++)+" completed-----------");
                worldcol=0;
                worldrow++;
            }
        }
    }
}