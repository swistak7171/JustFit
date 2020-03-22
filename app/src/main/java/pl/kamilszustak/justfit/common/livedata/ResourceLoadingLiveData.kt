package pl.kamilszustak.justfit.common.livedata

import androidx.lifecycle.LiveData
import pl.kamilszustak.justfit.common.data.Resource
import pl.kamilszustak.justfit.common.data.Status

class ResourceLoadingLiveData<T>(
    source: LiveData<Resource<T>>
) : LoadingLiveData<Resource<T>>(source, {
    it.status == Status.LOADING && it.data == null
})