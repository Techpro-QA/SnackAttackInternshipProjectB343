package snackattack.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.io.Serializable;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductPopulerListPojo implements Serializable {
	private List<ProductContentPojo> content;
	private ProductPageablePojo pageable;
	private int totalPages;
	private int totalElements;
	private boolean last;
	private int size;
	private int number;
	private ProductSortPojo sort;
	private int numberOfElements;
	private boolean first;
	private boolean empty;

	public void setContent(List<ProductContentPojo> content){
		this.content = content;
	}

	public List<ProductContentPojo> getContent(){
		return content;
	}

	public void setPageable(ProductPageablePojo pageable){
		this.pageable = pageable;
	}

	public ProductPageablePojo getPageable(){
		return pageable;
	}

	public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public void setTotalElements(int totalElements){
		this.totalElements = totalElements;
	}

	public int getTotalElements(){
		return totalElements;
	}

	public void setLast(boolean last){
		this.last = last;
	}

	public boolean isLast(){
		return last;
	}

	public void setSize(int size){
		this.size = size;
	}

	public int getSize(){
		return size;
	}

	public void setNumber(int number){
		this.number = number;
	}

	public int getNumber(){
		return number;
	}

	public void setSort(ProductSortPojo sort){
		this.sort = sort;
	}

	public ProductSortPojo getSort(){
		return sort;
	}

	public void setNumberOfElements(int numberOfElements){
		this.numberOfElements = numberOfElements;
	}

	public int getNumberOfElements(){
		return numberOfElements;
	}

	public void setFirst(boolean first){
		this.first = first;
	}

	public boolean isFirst(){
		return first;
	}

	public void setEmpty(boolean empty){
		this.empty = empty;
	}

	public boolean isEmpty(){
		return empty;
	}

	@Override
 	public String toString(){
		return 
			"ProductPopulerListPojo{" + 
			"content = '" + content + '\'' + 
			",pageable = '" + pageable + '\'' + 
			",totalPages = '" + totalPages + '\'' + 
			",totalElements = '" + totalElements + '\'' + 
			",last = '" + last + '\'' + 
			",size = '" + size + '\'' + 
			",number = '" + number + '\'' + 
			",sort = '" + sort + '\'' + 
			",numberOfElements = '" + numberOfElements + '\'' + 
			",first = '" + first + '\'' + 
			",empty = '" + empty + '\'' + 
			"}";
		}
}