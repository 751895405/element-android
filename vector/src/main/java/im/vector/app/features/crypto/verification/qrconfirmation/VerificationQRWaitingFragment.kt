/*
 * Copyright (c) 2020 New Vector Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.vector.app.features.crypto.verification.qrconfirmation

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import com.airbnb.mvrx.MvRx
import im.vector.app.R
import im.vector.app.core.extensions.cleanup
import im.vector.app.core.extensions.configureWith
import im.vector.app.core.platform.VectorBaseFragment
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.bottom_sheet_verification_child_fragment.*
import javax.inject.Inject

class VerificationQRWaitingFragment @Inject constructor(
        val controller: VerificationQRWaitingController
) : VectorBaseFragment() {

    @Parcelize
    data class Args(
            val isMe: Boolean,
            val otherUserName: String
    ) : Parcelable

    override fun getLayoutResId() = R.layout.bottom_sheet_verification_child_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        (arguments?.getParcelable(MvRx.KEY_ARG) as? Args)?.let {
            controller.update(it)
        }
    }

    override fun onDestroyView() {
        bottomSheetVerificationRecyclerView.cleanup()
        super.onDestroyView()
    }

    private fun setupRecyclerView() {
        bottomSheetVerificationRecyclerView.configureWith(controller, hasFixedSize = false, disableItemAnimation = true)
    }
}
