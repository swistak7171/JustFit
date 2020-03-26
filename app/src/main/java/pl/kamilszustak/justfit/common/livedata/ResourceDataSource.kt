package pl.kamilszustak.justfit.common.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pl.kamilszustak.justfit.common.data.Resource
import pl.kamilszustak.justfit.util.mapNotNull

class ResourceDataSource<T>(
    block: () -> LiveData<Resource<T>> = { MutableLiveData<Resource<T>>() }
) : RefreshableDataSource<Resource<T>>(block) {

    val data: LiveData<T> = this.result.mapNotNull {
        it.data
    }

    val isLoading: LiveData<Boolean> = ResourceLoadingLiveData(this.result)

    val error: SingleLiveData<String> = ResourceErrorLiveData(this.result)
}