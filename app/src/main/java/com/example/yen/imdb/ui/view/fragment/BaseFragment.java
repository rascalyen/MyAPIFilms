package com.example.yen.imdb.ui.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;
import com.example.yen.imdb.ui.dependency.HasComponent;


public abstract class BaseFragment extends Fragment {

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    /**
     * Shows a {@link android.widget.Toast} message.
     *
     * @param message An string representing a message to be shown.
     */
    void showToastMessage(String message) {
        try {
            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        }
        catch (NullPointerException ex) {}
    }

    /**
     * Gets a component for dependency injection by its type.
     */
    <C> C getComponent(Class<C> componentType) {
        return componentType.cast( ((HasComponent<C>) getActivity()).getComponent() );
    }

}
