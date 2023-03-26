package com.example.mobileapp.ui.send;

import androidx.fragment.app.Fragment;

public class SendFragment extends Fragment {

    private SendViewModel sendViewModel;







   /* public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);
       Button btn = getView().findViewById(R.id.update_account_settings_btn);
       btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SendFragment.class));
            }
        });



        final TextView textView = root.findViewById(R.id.text_send);
        sendViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;


   }
*/


}
