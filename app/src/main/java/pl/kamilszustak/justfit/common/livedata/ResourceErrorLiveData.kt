package pl.kamilszustak.justfit.common.livedata

import androidx.lifecycle.LiveData
import pl.kamilszustak.justfit.common.data.Resource
import pl.kamilszustak.justfit.common.data.Status

class ResourceErrorLiveData<T>(
    source: LiveData<Resource<T>>
) : ErrorLiveData<Resource<T>>(source, source.value?.message ?: "", {
    it.status == Status.ERROR
})