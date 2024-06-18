import androidx.lifecycle.LiveData
import com.example.a6112_final_project_kotlin.Item
import com.example.a6112_final_project_kotlin.ItemsDao

class ItemsRepository(private val itemsDao: ItemsDao) {

    val allItems: LiveData<List<Item>> = itemsDao.getAll()

    suspend fun insert(item: Item) {
        itemsDao.insert(item)
    }

    suspend fun update(item: Item) {
        itemsDao.update(item)
    }

    suspend fun delete(item: Item) {
        itemsDao.delete(item)
    }
}
