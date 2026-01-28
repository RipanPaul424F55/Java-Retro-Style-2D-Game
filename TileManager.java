import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class TileManager
{
    GamePanel gp;
    Tile[] tile;
    public int mapTileNum[][];
    public TileManager(GamePanel gp)
    {
        this.gp=gp;
        tile=new Tile[10];
        mapTileNum=new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        LoadMap("/world01.txt");
    }
    public void getTileImage()
    {
        try{
            tile[0]=new Tile();
            tile[0].image=ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
            tile[1]=new Tile();
            tile[1].image=ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].collision=true;
            tile[2]=new Tile();
            tile[2].image=ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tile[2].collision=true;
            tile[3]=new Tile();
            tile[3].image=ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
            tile[4]=new Tile();
            tile[4].image=ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[4].collision=true;
            tile[5]=new Tile();
            tile[5].image=ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void LoadMap(String filePath)
    {
        try{
            InputStream is=getClass().getResourceAsStream(filePath);
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            int col=0;
            int row=0;
            while(col<gp.maxWorldCol && row<gp.maxWorldRow)
            {
                String line=br.readLine();
                while(col<gp.maxWorldCol)
                {
                    String numbers[]=line.split(" ");
                    int num=Integer.parseInt(numbers[col]);
                    mapTileNum[col][row]=num;
                    col++;
                }
                if(col==gp.maxWorldCol)
                {
                    col=0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    /*public void draw(Graphics2D g2)// experimental, for reducing all 50x50 initialization
    {
        int tr=0,tc=0;
        int rowStart=(gp.player.worldY-gp.player.screenY)/gp.tileSize;
        int colStart=(gp.player.worldX-gp.player.screenX)/gp.tileSize;
        long x=System.nanoTime();
        for(int i=rowStart;i<(rowStart+13);i++)
        {
            for(int j=colStart;j<(colStart+17);j++)
            {
                //System.out.println("plot ("+tc+","+tr+")");
                int tileNum=mapTileNum[j][i];
                g2.drawImage(tile[tileNum].image,tc,tr,gp.tileSize,gp.tileSize,null);
                tc+=48;
            }
            tc=0;
            tr+=48;
        }
        System.out.println("Frequency :"+1/((System.nanoTime()-x)/Math.pow(10,9)));
        //System.exit(0);
        /*int worldCol=0;
        int worldRow=0;
        while(worldCol<gp.maxWorldCol && worldRow<gp.maxWorldRow)
        {
            int tileNum=mapTileNum[worldCol][worldRow];
            int worldX=worldCol*gp.tileSize;
            int worldY=worldRow*gp.tileSize;
            int screenX=worldX-gp.player.worldX+gp.player.screenX;
            int screenY=worldY-gp.player.worldY+gp.player.screenY;
            g2.drawImage(tile[tileNum].image,screenX,screenY,gp.tileSize,gp.tileSize,null);
            worldCol++;
            if(worldCol==gp.maxWorldCol)
            {
                worldCol=0;
                worldRow++;
            }
        }
    }*/
    public void draw(Graphics2D g2)//main Draw method
    {
        int worldCol=0;
        int worldRow=0;
        while(worldCol<gp.maxWorldCol && worldRow<gp.maxWorldRow)
        {
            int tileNum=mapTileNum[worldCol][worldRow];
            int worldX=worldCol*gp.tileSize;
            int worldY=worldRow*gp.tileSize;
            int screenX=worldX-gp.player.worldX+gp.player.screenX;//player.(screenx,screeny) is fixed ie,around384,288px
            //if we dont use player.(screenx,screeny) then the world value(row 21 col 23) will desplayed at the top left of the screen,not center of the screen
            //this player.(screenx,screeny) thakes that world(row 21,23) to the center of the screen
            int screenY=worldY-gp.player.worldY+gp.player.screenY;//player.(worldX,worldY) will decide where the view will be on the map(initialized with 1104,1008)
            if(worldX > gp.player.worldX - gp.player.screenX-gp.tileSize &&
                worldX < gp.player.worldX+gp.player.screenX+gp.tileSize &&
                worldY > gp.player.worldY-gp.player.screenY-gp.tileSize &&
                worldY < gp.player.worldY+gp.player.screenY+gp.tileSize)
            {
                g2.drawImage(tile[tileNum].image,screenX,screenY,gp.tileSize,gp.tileSize,null);//draw when screenX and screenY(in this loop) starts from 0,0.
            //(int this while loop, when screenX,sclscreenY(initialized in this loop) will lie between (0 to 768 {for x} and 0 to 576{for y})) it will be desplayed
            //rest other values of screenX,screenY not lies in that range,will not be visibled
            }
            worldCol++;
            if(worldCol==gp.maxWorldCol)
            {
                worldCol=0;
                worldRow++;
            }
        }
    }
}
/*public void draw(Graphics2D g2) FIXED CAMERA,for recovering
    {
        int col=0;
        int row=0;
        int x=0;
        int y=0;
        while(col<gp.maxWorldCol && row<gp.maxWorldRow)
        {
            int tileNum=mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image,x,y,gp.tileSize,gp.tileSize,null);
            col++;
            x+=gp.tileSize;
            if(col==gp.maxWorldCol)
            {
                col=0;
                x=0;
                row++;
                y+=gp.tileSize;
            }
        }
    }*/
       /*for(int i=0;i<gp.maxScreenRow;i++)
    for(int j=0;j<gp.maxScreenCol;j++)
    g2.drawImage(tile[0].image,j*gp.tileSize,i*gp.tileSize,gp.tileSize,gp.tileSize,null);*/