package aces.esprit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import aces.esprit.entity.Message;




public interface MessageRepository extends CrudRepository<Message, Integer> {
	
	@Query("select m from Message m where (m.sender.id=:idSender and m.receiver.id=:idReceiver) or"
			+ " (m.sender.id=:idReceiver and m.receiver.id=:idSender) order by date asc")
	List<Message> findBySenderAndReceiver(@Param ("idSender")int idSender, @Param("idReceiver") int idReceiver);
	
 
	@Query("select m from Message m where (m.sender.id=:idSender and m.receiver.id=:idReceiver) or"
			+ " (m.sender.id=:idReceiver and m.receiver.id=:idSender) order by date desc")
	List<Message> findBySenderAndReceiverdesc(@Param ("idSender")int idSender, @Param("idReceiver") int idReceiver);
}
