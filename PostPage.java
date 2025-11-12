package app;
import javax.swing.*;

public class PostPage extends JFrame {
    int topicId, userId;
    ForumDAO dao = new ForumDAO();
    JList<String> postList = new JList<>();

    public PostPage(int tId, int uId){
        topicId=tId; userId=uId;
        setTitle("Posts");
        setSize(400,300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JButton addBtn = new JButton("Add Post");
        addBtn.addActionListener(e -> {
            String msg = JOptionPane.showInputDialog("Enter Message:");
            if(msg!=null && !msg.isEmpty()) dao.addPost(topicId,userId,msg);
            loadPosts();
        });

        add(new JScrollPane(postList),"Center");
        add(addBtn,"South");

        loadPosts();
        setVisible(true);
    }

    void loadPosts(){
        postList.setListData(dao.getPosts(topicId).toArray(new String[0]));
    }
}
