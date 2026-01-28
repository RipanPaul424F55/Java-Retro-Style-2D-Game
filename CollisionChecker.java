public class CollisionChecker
{
    public GamePanel gp;
    public CollisionChecker(GamePanel gp)
    {
        this.gp=gp;
    }
    public void checkTile(Entity entity)
    {
        int entityLeftWorldX=entity.worldX+entity.solidArea.x;//col(23)+8px(shift);
        int entityRightWorldX=entity.worldX+entity.solidArea.x+entity.solidArea.width;//col(23)+8px(shift)+32px(solid area)
        int entityTopWorldY=entity.worldY+entity.solidArea.y;//row(21)+16px(shift)
        int entityBottomWorldY=entity.worldY+entity.solidArea.y+entity.solidArea.height;//row(21)+16px(shift)+32px(solid area)

        //world row col occupied by player rectangle
        int entityLeftCol=entityLeftWorldX/gp.tileSize;//nearly col(23)
        int entityRightCol=entityRightWorldX/gp.tileSize;//nearly col(24)
        int entityTopRow=entityTopWorldY/gp.tileSize;//nearly row(21)
        int entityBottomRow=entityBottomWorldY/gp.tileSize;//nearly row(22)


        int tileNum1,tileNum2;//for direction up or down, tileNum1 and tileNum2 checks two tiles are colliding left and right respectively
        switch(entity.direction)
        {
            case "up":
            entityTopRow=(entityTopWorldY-entity.speed)/gp.tileSize;
            tileNum1=gp.tile.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2=gp.tile.mapTileNum[entityRightCol][entityTopRow];
            if(gp.tile.tile[tileNum1].collision==true && gp.tile.tile[tileNum2].collision==true)
            {
                entity.collisionOn=true;
            }
            break;
            case "down":
            entityBottomRow=(entityBottomWorldY+entity.speed)/gp.tileSize;
            tileNum1=gp.tile.mapTileNum[entityLeftCol][entityBottomRow];
            tileNum2=gp.tile.mapTileNum[entityRightCol][entityBottomRow];
            if(gp.tile.tile[tileNum1].collision==true || gp.tile.tile[tileNum2].collision==true)
            {
                entity.collisionOn=true;
            }
            break;
            case "left":
            entityLeftCol=(entityLeftWorldX-entity.speed)/gp.tileSize;
            tileNum1=gp.tile.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2=gp.tile.mapTileNum[entityLeftCol][entityBottomRow];
            if(gp.tile.tile[tileNum1].collision==true || gp.tile.tile[tileNum2].collision==true)
            {
                entity.collisionOn=true;
            }
            break;
            case "right":
            entityRightCol=(entityRightWorldX+entity.speed)/gp.tileSize;
            tileNum1=gp.tile.mapTileNum[entityRightCol][entityTopRow];
            tileNum2=gp.tile.mapTileNum[entityRightCol][entityBottomRow];
            if(gp.tile.tile[tileNum1].collision==true || gp.tile.tile[tileNum2].collision==true)
            {
                entity.collisionOn=true;
            }
            break;
        }
    }
}