package app;
import javax.swing.*;

public class ForumPage extends JFrame {
    int userId;
    ForumDAO dao = new ForumDAO();
    JList<String> topicList = new JList<>();

    public ForumPage(int uid){
        userId = uid;
        setTitle("Forum");
        setSize(400,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton addTopicBtn = new JButton("New Topic");
        addTopicBtn.addActionListener(e -> {
            String t = JOptionPane.showInputDialog("Enter Topic Title:");
            if(t!=null && !t.isEmpty()) dao.createTopic(t,userId);
            loadTopics();
        });

        topicList.addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting()) openPosts();
        });

        add(new JScrollPane(topicList),"Center");
        add(addTopicBtn,"South");

        loadTopics();
        setVisible(true);
    }

    void loadTopics(){
        topicList.setListData(dao.getTopics().toArray(new String[0]));
    }

    void openPosts(){
        if(topicList.getSelectedValue()==null) return;
        int id = Integer.parseInt(topicList.getSelectedValue().split(" ")[0]);
        new PostPage(id,userId);
    }
}
