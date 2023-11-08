package algonquin.cst2335.guo00125;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {ChatMessage.class}, version = 1)
public abstract class MessageDataBase extends RoomDatabase {

    public abstract ChatMessageDAO cmDAO();
}
