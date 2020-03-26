package pl.kamilszustak.justfit.common.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow

open class RefreshableDataSource<T>(
    private var block: () -> LiveData<T> = { UniqueLiveData<T>() }
) {

    val result: MediatorLiveData<T> = MediatorLiveData()

    private var data: LiveData<T> = UniqueLiveData()

    init {
        getSourceData()
    }

    fun refresh() {
        result.removeSource(data)
        getSourceData()
    }

    private fun getSourceData() {
        data = block()
        result.addSource(data) {
            result.value = it
        }
    }

    fun setLiveDataSource(block: () -> LiveData<T>) {
        this.block = block
        refresh()
    }

    fun setFlowSource(block: () -> Flow<T>) {
        setLiveDataSource {
            block().asLiveData()
        }
    }
}