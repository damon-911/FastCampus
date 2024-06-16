package fastcampus.part5.chapter3.library.storage.usecase

import fastcampus.part5.chapter3.library.storage.IStorage
import javax.inject.Inject

class StorageClearUseCase @Inject constructor(
    private val storage: IStorage
) : IStorageClearUseCase {
    override fun invoke() {
        storage.clear()
    }
}
