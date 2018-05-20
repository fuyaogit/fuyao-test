package com.linktrust.fuyao.utils;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.wordnik.swagger.annotations.ApiModel;

/**   
*    
* 项目名称：wk-platform   
* 类名称：ListVO   
* 类描述：列表 VO 模板   
* 创建人：007   
* 创建时间：2016年6月21日 上午10:35:06   
* 修改人：007   
* 修改时间：2016年6月21日 上午10:35:06   
* 修改备注：   
* @version    
*    
*/ 
@ApiModel
public class ListVO<E> implements Serializable, Iterable<E>
{
    private static final long serialVersionUID = -4457395163935952182L;

    /** 真正的 List */
    protected List<E> list;

    /** 分页属性对象 */
    protected PageVO pageData;

    /** 额外数据 Map */
    protected Map<String, Object> extras;

    public static void main(String[] args) throws IOException
    {
        ListVO<String> l = new ListVO<String>();

        l.add("1");
        l.add("2");
        l.add("3");

        ListVO<String> list = new ListVO<String>();

        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");

        list.addAll(l);

        list.setPageData(new PageVO(103, 10));

    }

    public ListVO()
    {
        this.list = new ArrayList<E>();
    }

    public ListVO(List<E> list, PageVO pageData)
    {
        this.list = list;
        this.pageData = pageData;
    }

    public ListVO(List<E> list)
    {
        this.list = list;
    }

    public List<E> getList()
    {
        return list;
    }

    public void setList(List<E> list)
    {
        this.list = list;
    }

    public PageVO getPageData()
    {
        return pageData;
    }

    public void setPageData(PageVO pageData)
    {
        this.pageData = pageData;
    }

    public void addExtra(String key, Object value)
    {
        if(extras == null)
        {
            extras = CollectionUtil.newMap();
        }

        extras.put(key, value);
    }

    public Map<String, Object> getExtras()
    {
        return extras;
    }

    public int size()
    {
        if(list == null)
        {
            return 0;
        }
        return list.size();
    }

    public boolean isEmpty()
    {
        return this.size() == 0;
    }

    public boolean contains(Object o)
    {
        if(list == null)
        {
            return false;
        }
        return list.contains(o);
    }

    public Iterator<E> iterator()
    {
        if(list == null)
        {
            return null;
        }

        return list.iterator();
    }

    public E[] toArray()
    {
        return CollectionUtil.toArray(list);
    }

    public boolean add(E e)
    {
        if(list == null)
        {
            return false;
        }

        return list.add(e);
    }

    public boolean remove(Object o)
    {

        if(list == null)
        {
            return false;
        }
        return list.remove(o);
    }

    public boolean containsAll(Collection<?> c)
    {
        if(list == null)
        {
            return false;
        }
        return list.containsAll(c);
    }

    public boolean addAll(Collection<? extends E> c)
    {
        if(list == null)
        {
            return false;
        }

        return list.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends E> c)
    {
        if(list == null)
        {
            return false;
        }
        return list.addAll(index, c);
    }

    public boolean removeAll(Collection<?> c)
    {
        if(list == null)
        {
            return false;
        }
        return list.removeAll(c);
    }

    public boolean retainAll(Collection<?> c)
    {
        if(list == null)
        {
            return false;
        }
        return list.retainAll(c);
    }

    public boolean containsAll(ListVO<?> c)
    {
        if(list == null || c.getList() == null)
        {
            return false;
        }
        return list.containsAll(c.getList());
    }

    public boolean addAll(ListVO<? extends E> c)
    {
        if(list == null || c.getList() == null)
        {
            return false;
        }

        return list.addAll(c.getList());
    }

    public boolean addAll(int index, ListVO<? extends E> c)
    {
        if(list == null || c.getList() == null)
        {
            return false;
        }
        return list.addAll(index, c.getList());
    }

    public boolean removeAll(ListVO<?> c)
    {
        if(list == null || c.getList() == null)
        {
            return false;
        }
        return list.removeAll(c.getList());
    }

    public boolean retainAll(ListVO<?> c)
    {
        if(list == null || c.getList() == null)
        {
            return false;
        }

        return list.retainAll(c.getList());
    }

    public void clear()
    {
        if(list == null)
        {
            return;
        }
        list.clear();
    }

    public E get(int index)
    {
        if(list == null)
        {
            return null;
        }

        return list.get(index);
    }

    public E set(int index, E element)
    {
        if(list == null)
        {
            return null;
        }

        return list.set(index, element);
    }

    public void add(int index, E element)
    {
        if(list == null)
        {
            return;
        }

        list.add(index, element);
    }

    public E remove(int index)
    {
        if(list == null)
        {
            return null;
        }

        return list.remove(index);
    }

    public int indexOf(Object o)
    {
        if(list == null)
        {
            return -1;
        }

        return list.indexOf(o);
    }

    public int lastIndexOf(Object o)
    {
        if(list == null)
        {
            return -1;
        }

        return list.lastIndexOf(o);
    }

    public ListIterator<E> listIterator()
    {
        if(list == null)
        {
            return null;
        }
        return list.listIterator();
    }

    public ListIterator<E> listIterator(int index)
    {
        if(list == null)
        {
            return null;
        }
        return list.listIterator();
    }

    public List<E> subList(int fromIndex, int toIndex)
    {
        if(list == null)
        {
            return null;
        }

        return list.subList(fromIndex, toIndex);
    }
}
