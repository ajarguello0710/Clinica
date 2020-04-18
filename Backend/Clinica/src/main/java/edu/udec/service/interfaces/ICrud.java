package edu.udec.service.interfaces;

import java.util.List;

public interface ICrud <TDto, ID> {

	public List<TDto> get();
	
	public TDto getId(ID id);
	
	public TDto save(TDto objectSave);
	
	public TDto edit(TDto objectEdit);
	
	public void delete(ID id);
}
